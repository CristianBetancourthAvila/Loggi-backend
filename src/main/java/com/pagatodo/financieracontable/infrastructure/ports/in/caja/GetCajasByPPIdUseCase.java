package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;

import java.util.List;

public interface GetCajasByPPIdUseCase {
    List<Caja> findCajasByPPId(Integer programarPagoid) throws CajaNotFoundException;
}
