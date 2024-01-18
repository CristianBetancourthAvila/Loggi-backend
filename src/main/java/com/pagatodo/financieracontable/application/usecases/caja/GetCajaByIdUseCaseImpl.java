package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class GetCajaByIdUseCaseImpl implements GetCajaByIdUseCase {

    private final CajaPort cajaPort;

    @Transactional(readOnly = true)
    @Override
    public Caja findById(BigInteger id){
        return cajaPort.findById(id);
    }
}
