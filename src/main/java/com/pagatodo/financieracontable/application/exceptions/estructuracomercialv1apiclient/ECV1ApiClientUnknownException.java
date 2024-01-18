package com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient;

import com.pagatodo.commons.exceptions.UnknownException;

public class ECV1ApiClientUnknownException extends UnknownException {
    private static final long serialVersionUID = 7618035995184963675L;
    public ECV1ApiClientUnknownException(final Throwable cause) {
        super(ECV1ApiClientErrorCodes.ECV1_UNKNOWN_ERROR, cause);
    }
}
