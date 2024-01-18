package com.pagatodo.financieracontable.infrastructure.ports.in.cerrarcaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.CerrarCaja;

public interface CerrarCajaUseCase {

    CerrarCaja create(CerrarCaja cerrarCaja) throws BusinessException, NotFoundException;
}
