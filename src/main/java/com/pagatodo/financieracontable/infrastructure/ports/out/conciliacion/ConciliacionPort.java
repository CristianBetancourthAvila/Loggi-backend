package com.pagatodo.financieracontable.infrastructure.ports.out.conciliacion;

import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;

import java.util.List;

public interface ConciliacionPort {

    Conciliacion save(Conciliacion conciliacion);
    PageModel<List<Conciliacion>> findWithFilter(ConciliacionFilter conciliacionFilter, Integer rowsPerPage, Integer skip);
}
