package com.pagatodo.financieracontable.application.exceptions.librodiario;

import com.pagatodo.commons.exceptions.BusinessException;

public class LibroDiarioBusinessException extends BusinessException {

    private static final long serialVersionUID = -1584777917390081394L;

    public LibroDiarioBusinessException() {
        super(LibroDiarioErrorCodes.INVALID_TRANSACTION);
    }
}
