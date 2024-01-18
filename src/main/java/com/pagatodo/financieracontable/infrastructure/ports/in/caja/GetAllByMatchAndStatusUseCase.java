package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

import java.util.List;

public interface GetAllByMatchAndStatusUseCase {
    List<Caja> findAllByMatchesAndStatus(String filterText, Estado status);
}
