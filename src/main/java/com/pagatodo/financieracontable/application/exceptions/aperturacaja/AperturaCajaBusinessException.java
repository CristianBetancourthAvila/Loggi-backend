package com.pagatodo.financieracontable.application.exceptions.aperturacaja;

import com.pagatodo.commons.exceptions.BusinessException;

public class AperturaCajaBusinessException extends BusinessException {

    private static final long serialVersionUID = -1584770917390081394L;

    public AperturaCajaBusinessException() {
        super(AperturaCajaErrorCodes.INVALID_TRANSACTION);
    }
}
