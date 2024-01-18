package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.financieracontable.domain.models.Caja;

import java.util.List;

public interface CreateAllCajaUseCase {

    List<Caja> saveAll(List<Caja> request);
}
