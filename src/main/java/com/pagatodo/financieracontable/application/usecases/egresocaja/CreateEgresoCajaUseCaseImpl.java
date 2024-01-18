package com.pagatodo.financieracontable.application.usecases.egresocaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja.CreateEgresoCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja.EgresoCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class CreateEgresoCajaUseCaseImpl implements CreateEgresoCajaUseCase {

    private final EgresoCajaPort egresoCajaPort;
    private final ProgramarPagoPort programarPagoPort;
    private final ValidateStatusCajaUtils validateStatusCajaUtils;
    private final AperturaCajaPort aperturaCajaPort;
    private static final String CAJA_IS_NOT_OPEN = FinancieraContableErrorCodes.CAJA_IS_NOT_OPEN.getLocalizedMessage();

    @Transactional
    @Override
    public EgresoCaja create(EgresoCaja egresoCaja) throws BusinessException, NotFoundException {
        ProgramarPago programarPagoSaved = programarPagoPort.findById(egresoCaja.getProgramarPago().getId());
        boolean status = validateStatusCajaUtils.validateCajaAlreadyOpen(BigInteger.valueOf(egresoCaja.getAperturaCaja().getId()));
        VisitorValidator.of(programarPagoSaved)
                .and(CAJA_IS_NOT_OPEN,p -> status)
                .execute(EgresoCajaBusinessException::new);
        programarPagoSaved.setEstado(Estado.INACTIVO);
        programarPagoSaved.setCondicion(Condicion.PAGADO);
        ProgramarPago programarPagoSavedEdited = programarPagoPort.save(programarPagoSaved);
        egresoCaja.setFechaCreacion(LocalDate.now());
        egresoCaja.setHoraCreacion(LocalTime.now());
        egresoCaja.setProgramarPago(programarPagoSavedEdited);
        egresoCaja.setAperturaCaja(aperturaCajaPort.getLastRecord(BigInteger.valueOf(egresoCaja.getAperturaCaja().getId())));
        return egresoCajaPort.save(egresoCaja);
    }
}
