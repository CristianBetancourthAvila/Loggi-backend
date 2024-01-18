package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.UpdateCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCajaUseCaseImpl implements UpdateCajaUseCase {

    private final CajaPort cajaPort;

    @Transactional
    @Override
    public Caja update(Caja caja) throws NotFoundException {
        return cajaPort.update(caja);
    }
}
