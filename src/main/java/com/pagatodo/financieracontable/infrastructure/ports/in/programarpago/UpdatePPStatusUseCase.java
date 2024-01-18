package com.pagatodo.financieracontable.infrastructure.ports.in.programarpago;

import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;

public interface UpdatePPStatusUseCase {
    void updateStatus(Integer id) throws ProgramarPagoNotFoundException;
}
