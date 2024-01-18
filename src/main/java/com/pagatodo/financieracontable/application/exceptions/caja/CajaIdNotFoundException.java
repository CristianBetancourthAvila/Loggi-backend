package com.pagatodo.financieracontable.application.exceptions.caja;

import com.pagatodo.commons.exceptions.NotFoundException;

public class CajaIdNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 7628035905104963675L;
    public CajaIdNotFoundException() {
        super(CajaErrorCodes.CAJA_ID_NOT_FOUND);
    }
}
