package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;

public interface UpdateCajaUseCase {
    Caja update(Caja caja) throws NotFoundException;
}
