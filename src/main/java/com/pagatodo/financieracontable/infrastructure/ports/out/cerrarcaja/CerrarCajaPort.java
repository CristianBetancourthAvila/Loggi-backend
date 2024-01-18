package com.pagatodo.financieracontable.infrastructure.ports.out.cerrarcaja;

import com.pagatodo.financieracontable.domain.models.CerrarCaja;

public interface CerrarCajaPort {

    CerrarCaja create(CerrarCaja cerrarCaja);
}
