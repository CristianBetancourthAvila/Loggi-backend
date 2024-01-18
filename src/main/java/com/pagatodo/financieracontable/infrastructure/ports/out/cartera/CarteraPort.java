package com.pagatodo.financieracontable.infrastructure.ports.out.cartera;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.Cartera;

public interface CarteraPort {
    Cartera save(Cartera cartera);

    Cartera findCarteraByVendedorId(Integer sellerId);

    Cartera update(Cartera cartera) throws NotFoundException;

    Cartera findById(Integer id);
}
