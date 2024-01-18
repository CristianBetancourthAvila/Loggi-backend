package com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient;

import com.pagatodo.commons.exceptions.UnknownException;

public class AVV1ApiClientUnknownException extends UnknownException {
    private static final long serialVersionUID = 7618035995184963675L;
    public AVV1ApiClientUnknownException(final Throwable cause) {
        super(AVV1ApiClientErrorCodes.AVV1_UNKNOWN_ERROR, cause);
    }
}
