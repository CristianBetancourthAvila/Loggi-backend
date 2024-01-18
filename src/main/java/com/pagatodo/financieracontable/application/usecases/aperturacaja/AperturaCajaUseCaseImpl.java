package com.pagatodo.financieracontable.application.usecases.aperturacaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.in.aperturacaja.AperturaCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import lombok.extern.log4j.Log4j2;
@Log4j2
@RequiredArgsConstructor
@Service
public class AperturaCajaUseCaseImpl implements AperturaCajaUseCase {

    private static final String OBSERVACIONES_FIELD_REQUIRED = AperturaCajaErrorCodes.OBSERVACIONES_FIELD_REQUIRED.getLocalizedMessage();

    private static final String INVALID_AMOUNT = AperturaCajaErrorCodes.INVALID_AMOUNT.getLocalizedMessage();

    private static final String INACTIVATED_CAJA = FinancieraContableErrorCodes.INACTIVATED_CAJA.getLocalizedMessage();

    private static final String CAJA_ALREADY_OPEN = AperturaCajaErrorCodes.CAJA_ALREADY_OPEN.getLocalizedMessage();

    private static final String CAJA_DOES_NOT_MATCH = AperturaCajaErrorCodes.CAJA_DOES_NOT_MATCH.getLocalizedMessage();

    private final AperturaCajaPort port;
    private final ValidateStatusCajaUtils validateStatusCajaUtils;

    private final CajaPort cajaPort;

    @Transactional
    @Override
    public AperturaCaja create(AperturaCaja aperturaCaja) throws BusinessException, NotFoundException {
        log.info("AperturaCajaUseCase::create::INI");

        boolean status = validateStatusCajaUtils.validateCajaAlreadyOpen(aperturaCaja.getCaja().getId());
        Caja caja = cajaPort.findById(aperturaCaja.getCaja().getId());
        AperturaCaja lastRecord = this.port.getLastRecord(caja.getId());
        VisitorValidator.of(aperturaCaja)
                .and(CAJA_DOES_NOT_MATCH, ap -> Objects.equals(caja.getId(), ap.getCaja().getId()))
                .and(CAJA_ALREADY_OPEN,p -> !status)
                .and(INACTIVATED_CAJA, ap -> validateStatusCaja(caja.getEstado()))
                .and(OBSERVACIONES_FIELD_REQUIRED,this::validateFieldObservaciones)
                .and(INVALID_AMOUNT, this::validateAmountEqualWithInitialBalance)
                .execute(AperturaCajaBusinessException::new);
        aperturaCaja.setId(null);
        aperturaCaja.setEstado(true);
        aperturaCaja.setSaldoAnterior(lastRecord != null ? lastRecord.getSaldoInicial():0L);
        aperturaCaja.setFechaApertura(LocalDate.now());
        aperturaCaja.setHoraApertura(LocalTime.now());

        log.info("AperturaCajaUseCase::create::FIN");
        return this.port.create(aperturaCaja);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean validateStatus(BigInteger cajaId) throws NotFoundException {
        log.info("AperturaCajaUseCase::validateStatus::INI");

        boolean confirmation = validateStatusCajaUtils.validateCajaAlreadyOpen(cajaId);

        log.info("AperturaCajaUseCase::validateStatus::FIN");
        return confirmation;
    }

    @Transactional(readOnly = true)
    @Override
    public AperturaCaja findLastRecord(BigInteger cajaId) {
        log.info("AperturaCajaUseCase::findLastRecord::INI");

        AperturaCaja lastRecord = this.port.getLastRecord(cajaId);

        log.info("AperturaCajaUseCase::findLastRecord::FIN");

        return lastRecord;
    }

    @Transactional
    @Override
    public void updateStatus(Long id) throws NotFoundException {
        AperturaCaja currentAperturaCaja = port.findById(id);
        if(currentAperturaCaja == null){
            AperturaCajaNotFoundException errorNotFound = new AperturaCajaNotFoundException();
            errorNotFound.addParams(id);
            throw errorNotFound;
        }
        port.updateStatus(id, false);
    }


    @Transactional(readOnly = true)
    @Override
    public Caja findAssignedCajaByCajaId(BigInteger cajaId) {
        log.info("AperturaCajaUseCase::findAssignedCajaBySellerId::INI");

        Caja caja = cajaPort.findById(cajaId);

        log.info("AperturaCajaUseCase::findAssignedCajaBySellerId::FIN");
        return caja;
    }


    private boolean validateStatusCaja(Estado estado){return estado == Estado.ACTIVO;}

    private boolean validateFieldObservaciones(AperturaCaja aperturaCaja){
        if(aperturaCaja.getPremios()!= null || aperturaCaja.getOtros()!= null){
            return aperturaCaja.getObservaciones() != null;
        }
        return true;
    }

    private boolean validateAmountEqualWithInitialBalance(AperturaCaja aperturaCaja){
        long balance = aperturaCaja.getBilletes() + aperturaCaja.getMonedas();

        if(aperturaCaja.getPremios()!=null){
            balance+= aperturaCaja.getPremios();
        }

        if(aperturaCaja.getOtros()!=null){
            balance+= aperturaCaja.getOtros();
        }

        return Objects.equals(aperturaCaja.getSaldoInicial(), balance);
    }
}
