package com.pagatodo.financieracontable.application.exceptions.financieracontable;

import com.pagatodo.commons.exceptions.UnknownException;

public class FinancieraContableUnknownException extends UnknownException {
    private static final long serialVersionUID = -1584770917390080394L;

    public FinancieraContableUnknownException(Throwable cause) {
        super(FinancieraContableErrorCodes.UNKNOWN_FINANCIERA_CONTABLE_ERROR, cause);
    }
}
