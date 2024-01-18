package com.pagatodo.financieracontable.infrastructure.ports.in.caja;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.filter.CajaFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import java.util.List;

public interface GetCajaByCriteriaUseCase {
    PageModel<List<Caja>> findWithFiler(CajaFilter cajaFilter, Integer rowsPerPage, Integer skip) throws CajaNotFoundException;
}
