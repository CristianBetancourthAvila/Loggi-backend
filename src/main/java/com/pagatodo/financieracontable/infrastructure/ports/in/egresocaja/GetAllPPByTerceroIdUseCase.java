package com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja;

import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.records.PageModel;

import java.math.BigInteger;
import java.util.List;

public interface GetAllPPByTerceroIdUseCase {
    PageModel<List<ProgramarPago>> findByTerceroId(BigInteger id, Integer rowsPerPage, Integer skip) throws TerceroNotFoundException;
}
