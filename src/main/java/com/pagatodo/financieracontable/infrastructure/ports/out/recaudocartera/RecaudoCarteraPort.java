package com.pagatodo.financieracontable.infrastructure.ports.out.recaudocartera;

import com.pagatodo.financieracontable.domain.models.RecaudoCartera;

public interface RecaudoCarteraPort {
    RecaudoCartera save(RecaudoCartera recaudoCartera);
}
