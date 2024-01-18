package com.pagatodo.financieracontable.infrastructure.ports.in.conciliacion;

import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.domain.models.Conciliacion;

public interface CreateConciliacionUseCase {

    Conciliacion create(Conciliacion conciliacion) throws ConciliacionBusinessException;
}
