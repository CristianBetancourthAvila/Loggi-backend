package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajasByPPIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCajasByPPIdUseCaseImpl implements GetCajasByPPIdUseCase {

    private final CajaPort cajaPort;

    @Transactional(readOnly = true)
    @Override
    public List<Caja> findCajasByPPId(Integer programarPagoid) throws CajaNotFoundException {
        return cajaPort.findCajasByProgramarPagoid(programarPagoid);
    }
}
