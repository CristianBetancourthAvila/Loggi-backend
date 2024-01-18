package com.pagatodo.financieracontable.application.usecases.cartera;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraBusinessException;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraNotFoundByVendedorIdException;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.infrastructure.ports.in.cartera.CarteraUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.cartera.CarteraPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CarteraUseCaseImpl implements CarteraUseCase {

    private static final String BALANCE_LESS_THAN_ZERO = CarteraErrorCodes.BALANCE_LESS_THAN_ZERO.getLocalizedMessage();

    private final CarteraPort carteraPort;

    @Transactional
    @Override
    public Cartera create(Cartera cartera) throws BusinessException {
        VisitorValidator.of(cartera)
                        .and(BALANCE_LESS_THAN_ZERO, c-> c.getSaldo()>0)
                        .execute(CarteraBusinessException::new);
        cartera.setFechaCreacion(LocalDate.now());
        cartera.setHoraCreacion(LocalTime.now());
        cartera.setVentasDia(0L);
        cartera.setId(null);
        cartera.setVentaDiaLiquidada(0L);
        return carteraPort.save(cartera);
    }

    @Transactional(readOnly = true)
    @Override
    public Cartera findCarteraByVendedorId(Integer sellerId) throws NotFoundException {
        Cartera cartera = carteraPort.findCarteraByVendedorId(sellerId);
        if(cartera == null){
            CarteraNotFoundByVendedorIdException errorNotFound = new CarteraNotFoundByVendedorIdException();
            errorNotFound.addParams(sellerId);
            throw errorNotFound;
        }
        return cartera;
    }

    @Transactional
    @Override
    public Cartera update(Cartera cartera) throws NotFoundException {
        return carteraPort.update(cartera);
    }
}
