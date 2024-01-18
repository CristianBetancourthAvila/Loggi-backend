package com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;

public interface CreateEgresoCajaUseCase {
    EgresoCaja create(EgresoCaja egresoCaja) throws BusinessException, NotFoundException;
}
