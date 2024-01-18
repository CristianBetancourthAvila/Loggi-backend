package com.pagatodo.financieracontable.infrastructure.ports.in.librodiario;

import com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioBusinessException;
import com.pagatodo.financieracontable.domain.models.LibroDiario;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import java.util.List;

public interface GetLibroDiarioByCriteriaUseCase {
    PageModel<List<LibroDiario>> findWithFiler(LibroDiarioFilter libroDiarioFilter, Integer rowsPerPage, Integer skip) throws LibroDiarioBusinessException;
}
