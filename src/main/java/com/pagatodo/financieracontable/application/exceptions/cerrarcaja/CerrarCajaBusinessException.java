package com.pagatodo.financieracontable.application.exceptions.cerrarcaja;

import com.pagatodo.commons.exceptions.BusinessException;

public class CerrarCajaBusinessException extends BusinessException {

    private static final long serialVersionUID = -1584770917390081394L;

    public CerrarCajaBusinessException() {
        super(CerrarCajaErrorCodes.INVALID_TRANSACTION);
    }
}
