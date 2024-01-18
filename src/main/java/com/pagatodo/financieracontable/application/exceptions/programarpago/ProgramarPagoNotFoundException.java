package com.pagatodo.financieracontable.application.exceptions.programarpago;

import com.pagatodo.commons.exceptions.NotFoundException;

public class ProgramarPagoNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 7628035997104963675L;
    public ProgramarPagoNotFoundException() {
        super(ProgramarPagoErrorCodes.PROGRAMAR_PAGO_NOT_FOUND);
    }
}
