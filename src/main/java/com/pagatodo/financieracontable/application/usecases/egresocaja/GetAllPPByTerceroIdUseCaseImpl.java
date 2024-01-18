package com.pagatodo.financieracontable.application.usecases.egresocaja;

import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja.GetAllPPByTerceroIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllPPByTerceroIdUseCaseImpl implements GetAllPPByTerceroIdUseCase {

    private final ProgramarPagoPort programarPagoPort;
    @Override
    public PageModel<List<ProgramarPago>> findByTerceroId(BigInteger id, Integer rowsPerPage, Integer skip) throws TerceroNotFoundException {
        List<ProgramarPago> programarPagos = programarPagoPort.findAllByTerceroId(id);
        List<ProgramarPago> paginatedResult = programarPagos.subList(skip, Math.min(skip + rowsPerPage, programarPagos.size()));
        return new PageModel<>(paginatedResult, BigInteger.valueOf(programarPagos.size()));
    }
}
