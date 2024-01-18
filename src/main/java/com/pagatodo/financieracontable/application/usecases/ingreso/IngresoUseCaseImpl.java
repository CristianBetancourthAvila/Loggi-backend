package com.pagatodo.financieracontable.application.usecases.ingreso;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoBusinessException;
import com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.parametrizacionconcepto.ParametrizacionConceptoNotFoundException;
import com.pagatodo.financieracontable.application.usecases.commons.CreateAnulacionUtils;
import com.pagatodo.financieracontable.application.usecases.commons.GenerateFileUtils;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.domain.models.vouchers.IngresoVoucher;
import com.pagatodo.financieracontable.infrastructure.ports.in.ingreso.IngresoUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.ingreso.IngresoPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

@Log4j2
@RequiredArgsConstructor
@Service
public class IngresoUseCaseImpl implements IngresoUseCase {

    private static final String INACTIVATED_CAJA = FinancieraContableErrorCodes.INACTIVATED_CAJA.getLocalizedMessage();
    private static final String CAJA_IS_NOT_OPEN = FinancieraContableErrorCodes.CAJA_IS_NOT_OPEN.getLocalizedMessage();

    private static final String INGRESO_NOT_NULLABLE = IngresoErrorCodes.INGRESO_NOT_NULLABLE.getLocalizedMessage();

    private static final String MOTIVO_ANULACION_NOT_NULL = EgresoCajaErrorCodes.MOTIVO_ANULACION_NOT_NULL.getLocalizedMessage();

    private static final String REPORT_TEMPLATE_NAME= "ingreso_comprobante_pdf.jrxml";
    private static final String BASE_TEMPLATE_TITLE= "registro-ingreso-comprobante";

    private final IngresoPort port;
    private final AperturaCajaPort aperturaCajaPort;

    private final ParametrizacionConceptoPort parametrizacionConceptoPort;
    private final ValidateStatusCajaUtils validateStatusCajaUtils;

    private final CreateAnulacionUtils createAnulacionUtils;


    @Transactional
    @Override
    public Ingreso create(Ingreso ingreso) throws BusinessException, NotFoundException{
        log.info("IngresoUseCase::create::INI");

        AperturaCaja aperturaCaja = aperturaCajaPort.findById(ingreso.getAperturaCaja().getId());

        if(aperturaCaja == null){
            AperturaCajaNotFoundException errorNotFound = new AperturaCajaNotFoundException();
            errorNotFound.addParams(ingreso.getAperturaCaja().getId());
            throw errorNotFound;
        }

        Caja caja = aperturaCaja.getCaja();

        boolean status = validateStatusCajaUtils.validateCajaAlreadyOpen(aperturaCaja.getCaja().getId());

        VisitorValidator.of(caja)
                        .and(CAJA_IS_NOT_OPEN,p -> status)
                        .and(INACTIVATED_CAJA, c -> c.getEstado().equals(Estado.ACTIVO))
                        .execute(IngresoBusinessException::new);

        AperturaCaja lastRecord = aperturaCajaPort.getLastRecord(caja.getId());
        ingreso.setAperturaCaja(lastRecord);
        ingreso.setId(null);
        ingreso.setHoraCreacion(LocalTime.now());
        ingreso.setFechaCreacion(LocalDate.now());

        log.info("IngresoUseCase::create::FIN");

        return this.port.save(ingreso);

    }

    @Transactional
    @Override
    public void updateCancellationReason(Integer id, String cancellationReason) throws NotFoundException,BusinessException {
        log.info("IngresoUseCase::updateCancellationReason::INI");

        Ingreso ingreso = this.port.findById(id);
        if(ingreso == null){
          throw new IngresoIdNotFoundException();
        }

        ParametrizacionConcepto parametrizacionConcepto = parametrizacionConceptoPort.findById(ingreso.getParametrizacionConcepto().getId());

        if(parametrizacionConcepto == null){
            ParametrizacionConceptoNotFoundException errorNotFound =  new ParametrizacionConceptoNotFoundException();
            errorNotFound.addParams(ingreso.getParametrizacionConcepto().getId());
            throw errorNotFound;
        }

        VisitorValidator.of(parametrizacionConcepto)
                        .and(INGRESO_NOT_NULLABLE, ParametrizacionConcepto::getAnulable)
                        .and(MOTIVO_ANULACION_NOT_NULL, p -> !(cancellationReason == null || cancellationReason.isBlank()))
                        .execute(IngresoBusinessException::new);

        createAnulacionUtils.create(id, TipoMovimiento.INGRESO);

        log.info("IngresoUseCase::updateCancellationReason::FIN");

        this.port.updateCancellationReason(id, cancellationReason);
    }

    @Override
    public FileReport generateVoucher(IngresoVoucher voucher) throws JRException {
        HashMap<String, Object> parameters = new HashMap<>();
        String finalName = BASE_TEMPLATE_TITLE + "-" + voucher.getComprobante();
        parameters.put("comprobante", voucher.getComprobante()  != null ? voucher.getComprobante(): "");
        parameters.put("fechaHora", voucher.getFechaHora()  != null ? voucher.getFechaHora(): "");
        parameters.put("recibido", voucher.getRecibido()  != null ? voucher.getRecibido().toUpperCase(): "" );
        parameters.put("tipoDocumento", voucher.getTipoDocumento()  != null ? voucher.getTipoDocumento(): "");
        parameters.put("numeroDocumento", voucher.getNumeroDocumento()  != null ? voucher.getNumeroDocumento(): "");
        parameters.put("valor", voucher.getValor()  != null ? voucher.getValor(): "" );
        parameters.put("concepto", voucher.getConcepto()  != null ? voucher.getConcepto(): "");
        parameters.put("detalle", voucher.getDetalle()  != null ? voucher.getDetalle(): "");
        parameters.put("observacion", voucher.getObservacion() != null ? voucher.getObservacion(): "");
        return GenerateFileUtils.exportFileAsPDF(REPORT_TEMPLATE_NAME,parameters,finalName, null);
    }
}
