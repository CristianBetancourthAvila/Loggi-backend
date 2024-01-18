package com.pagatodo.financieracontable.application.exceptions.caja;

import com.pagatodo.commons.exceptions.NotFoundException;

public class CajaNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 7628035995104963675L;
    public CajaNotFoundException() {
        super(CajaErrorCodes.CAJA_NOT_FOUND);
    }
}