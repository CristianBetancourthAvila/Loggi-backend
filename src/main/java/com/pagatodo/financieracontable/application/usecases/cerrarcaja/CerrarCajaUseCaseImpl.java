package com.pagatodo.financieracontable.application.usecases.cerrarcaja;


import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.cerrarcaja.CerrarCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.cerrarcaja.CerrarCajaErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.CerrarCaja;
import com.pagatodo.financieracontable.infrastructure.ports.in.cerrarcaja.CerrarCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.cerrarcaja.CerrarCajaPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@Service
public class CerrarCajaUseCaseImpl implements CerrarCajaUseCase {

    private static final String OBSERVACIONES_FIELD_REQUIRED = AperturaCajaErrorCodes.OBSERVACIONES_FIELD_REQUIRED.getLocalizedMessage();

    private static final String INVALID_AMOUNT = CerrarCajaErrorCodes.INVALID_AMOUNT.getLocalizedMessage();

    private static final String CAJA_IS_NOT_OPEN = FinancieraContableErrorCodes.CAJA_IS_NOT_OPEN.getLocalizedMessage();

    private final CerrarCajaPort cerrarCajaPort;

    private final ValidateStatusCajaUtils validateStatusCajaUtils;

    private final AperturaCajaPort aperturaCajaPort;

    @Transactional
    @Override
    public CerrarCaja create(CerrarCaja cerrarCaja) throws BusinessException, NotFoundException {
        AperturaCaja aperturaCaja = aperturaCajaPort.findById(cerrarCaja.getAperturaCaja().getId());
        if(aperturaCaja == null){
            AperturaCajaNotFoundException errorNotFound = new AperturaCajaNotFoundException();
            errorNotFound.addParams(cerrarCaja.getAperturaCaja().getId());
            throw errorNotFound;
        }
        boolean status = validateStatusCajaUtils.validateCajaAlreadyOpen(aperturaCaja.getCaja().getId());
        VisitorValidator.of(cerrarCaja)
                        .and(CAJA_IS_NOT_OPEN, p-> status)
                        .and(OBSERVACIONES_FIELD_REQUIRED,this::validateFieldObservaciones)
                        .and(INVALID_AMOUNT, this::validateAmountEqualWithFinalBalance)
                        .execute(CerrarCajaBusinessException::new);
        cerrarCaja.setId(null);
        cerrarCaja.setFechaCierre(LocalDate.now());
        cerrarCaja.setHoraCierre(LocalTime.now());
        return cerrarCajaPort.create(cerrarCaja);
    }

    private boolean validateFieldObservaciones(CerrarCaja cerrarCaja){
        if(cerrarCaja.getPremios()!= null || cerrarCaja.getOtros()!= null){
            return cerrarCaja.getObservaciones() != null;
        }
        return true;
    }

    private boolean validateAmountEqualWithFinalBalance(CerrarCaja cerrarCaja){
        long balance = cerrarCaja.getBilletes() + cerrarCaja.getMonedas();

        if(cerrarCaja.getPremios()!=null){
            balance+= cerrarCaja.getPremios();
        }

        if(cerrarCaja.getOtros()!=null){
            balance+= cerrarCaja.getOtros();
        }

        return Objects.equals(cerrarCaja.getSaldoFinal(), balance);
    }
}
