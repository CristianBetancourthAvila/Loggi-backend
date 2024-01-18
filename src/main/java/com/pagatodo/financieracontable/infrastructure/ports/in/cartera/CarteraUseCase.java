package com.pagatodo.financieracontable.infrastructure.ports.in.cartera;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.Cartera;

public interface CarteraUseCase {

    Cartera create(Cartera cartera) throws BusinessException;

    Cartera findCarteraByVendedorId(Integer sellerId) throws NotFoundException;

    Cartera update(Cartera cartera) throws NotFoundException;

}
