package com.pagatodo.financieracontable.application.exceptions.aperturacaja;

import com.pagatodo.commons.exceptions.NotFoundException;

public class AperturaCajaNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 8628035905104963674L;

    public AperturaCajaNotFoundException() {super(AperturaCajaErrorCodes.APERTURA_CAJA_NOT_FOUND);}
}
