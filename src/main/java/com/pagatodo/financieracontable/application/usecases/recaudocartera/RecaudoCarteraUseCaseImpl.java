package com.pagatodo.financieracontable.application.usecases.recaudocartera;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.reacudocartera.RecaudoCarteraBusinessException;
import com.pagatodo.financieracontable.application.usecases.commons.GenerateFileUtils;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.RecaudoCartera;
import com.pagatodo.financieracontable.domain.models.vouchers.RecaudoCarteraVoucher;
import com.pagatodo.financieracontable.infrastructure.ports.in.recaudocartera.RecaudoCarteraUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.cartera.CarteraPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.recaudocartera.RecaudoCarteraPort;
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
public class RecaudoCarteraUseCaseImpl implements RecaudoCarteraUseCase {
    private static final String CAJA_IS_NOT_OPEN = FinancieraContableErrorCodes.CAJA_IS_NOT_OPEN.getLocalizedMessage();
    private static final String REPORT_TEMPLATE_NAME= "recaudo_comprobante_pdf.jrxml";
    private static final String BASE_TEMPLATE_TITLE= "recaudo-comprobante";

    private final CarteraPort carteraPort;

    private final AperturaCajaPort aperturaCajaPort;

    private final RecaudoCarteraPort recaudoCarteraPort;

    private final ValidateStatusCajaUtils validateStatusCajaUtils;

    @Transactional
    @Override
    public RecaudoCartera create(RecaudoCartera recaudoCartera) throws NotFoundException, BusinessException {
        log.info("RecaudoCarteraUseCase::create::INI");

        AperturaCaja aperturaCaja = aperturaCajaPort.findById(recaudoCartera.getAperturaCaja().getId());
        Cartera cartera = carteraPort.findById(recaudoCartera.getCartera().getId());
        if(aperturaCaja == null){
            AperturaCajaNotFoundException errorNotFound = new AperturaCajaNotFoundException();
            errorNotFound.addParams(recaudoCartera.getAperturaCaja().getId());
            throw errorNotFound;
        }
        if(cartera == null){
            CarteraNotFoundException errorNotFound = new CarteraNotFoundException();
            errorNotFound.addParams(recaudoCartera.getCartera().getId());
            throw errorNotFound;
        }
        boolean confirmation = validateStatusCajaUtils.validateCajaAlreadyOpen(aperturaCaja.getCaja().getId());
        VisitorValidator.of(confirmation)
                        .and(CAJA_IS_NOT_OPEN, c -> c)
                        .execute(RecaudoCarteraBusinessException::new);
        recaudoCartera.setAperturaCaja(aperturaCajaPort.getLastRecord(aperturaCaja.getCaja().getId()));
        recaudoCartera.setCartera(cartera);
        recaudoCartera.setId(null);
        recaudoCartera.setFechaCreacion(LocalDate.now());
        recaudoCartera.setHoraCreacion(LocalTime.now());

        log.info("RecaudoCarteraUseCase::create::FIN");
        return recaudoCarteraPort.save(recaudoCartera);
    }

    @Override
    public FileReport generateVoucher(RecaudoCarteraVoucher voucher) throws JRException {
        HashMap<String, Object> parameters = new HashMap<>();
        String finalName = BASE_TEMPLATE_TITLE + "-" + voucher.getComprobante();
        parameters.put("comprobante", voucher.getComprobante()  != null ? voucher.getComprobante(): "");
        parameters.put("caja", voucher.getCaja() != null ? voucher.getCaja(): "");
        parameters.put("zona", voucher.getZona()  != null ? voucher.getZona(): "");
        parameters.put("fechaHora", voucher.getFechaHora()  != null ? voucher.getFechaHora(): "");
        parameters.put("recibido", voucher.getRecibido()  != null ? voucher.getRecibido().toUpperCase(): "" );
        parameters.put("tipoDocumento", voucher.getTipoDocumento()  != null ? voucher.getTipoDocumento(): "");
        parameters.put("numeroDocumento", voucher.getNumeroDocumento()  != null ? voucher.getNumeroDocumento(): "");
        parameters.put("valor", voucher.getValor()  != null ? voucher.getValor(): "" );
        parameters.put("medioPago", voucher.getMedioPago()  != null ? voucher.getMedioPago(): "");
        parameters.put("detalle", voucher.getDetalle()  != null ? voucher.getDetalle(): "");
        return GenerateFileUtils.exportFileAsPDF(REPORT_TEMPLATE_NAME,parameters,finalName, null);
    }
}
