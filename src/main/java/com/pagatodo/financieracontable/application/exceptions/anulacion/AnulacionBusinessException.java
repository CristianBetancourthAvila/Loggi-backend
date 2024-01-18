package com.pagatodo.financieracontable.application.exceptions.anulacion;

import com.pagatodo.commons.exceptions.BusinessException;

public class AnulacionBusinessException extends BusinessException {

    private static final long serialVersionUID = -1564770917390081394L;

    public AnulacionBusinessException() {
        super(AnulacionErrorCodes.ERROR_ANULACION);
    }
}
