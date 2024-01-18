package com.pagatodo.financieracontable.infrastructure.ports.in.programarpago;

import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.filter.ProgramarPagoFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;

import java.util.List;

public interface GetPPByCriteriaUseCase {
    PageModel<List<ProgramarPago>> findWithFiler(ProgramarPagoFilter programarPagoFilter, Integer rowsPerPage, Integer skip);
}
