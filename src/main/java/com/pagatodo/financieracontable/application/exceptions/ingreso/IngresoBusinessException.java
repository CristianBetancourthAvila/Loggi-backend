package com.pagatodo.financieracontable.application.exceptions.ingreso;

import com.pagatodo.commons.exceptions.BusinessException;

public class IngresoBusinessException extends BusinessException {

    private static final long serialVersionUID = -1584770917360081394L;

    public IngresoBusinessException() {
        super(IngresoErrorCodes.ERROR_INGRESO);
    }
}
