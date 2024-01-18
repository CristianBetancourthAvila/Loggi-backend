package com.pagatodo.financieracontable.application.exceptions.egresocaja;

import com.pagatodo.commons.exceptions.NotFoundException;

public class EgresoCajaNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 7628035995104963675L;
    public EgresoCajaNotFoundException() {
        super(EgresoCajaErrorCodes.EGRESO_CAJA_NOT_FOUND);
    }
}
