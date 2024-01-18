package com.pagatodo.financieracontable.infrastructure.ports.in.conciliacion;

import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import java.util.List;

public interface GetConciliacionByCriteriaUseCase {
    PageModel<List<Conciliacion>> findWithFiler(ConciliacionFilter conciliacionFilter, Integer rowsPerPage, Integer skip) throws ConciliacionBusinessException;
}
