package com.pagatodo.financieracontable.application.exceptions.programarpago;

import com.pagatodo.commons.exceptions.NotFoundException;

public class TerceroNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 7628035997104963675L;
    public TerceroNotFoundException() {
        super(ProgramarPagoErrorCodes.TERCERO_NOT_FOUND);
    }
}