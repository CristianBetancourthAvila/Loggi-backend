package com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient;

import com.pagatodo.commons.exceptions.ConnectionException;

public class AVV1ApiClientConnectionException extends ConnectionException {

    private static final long serialVersionUID = 2642333535847059143L;

    public AVV1ApiClientConnectionException(Throwable cause) {
        super(AVV1ApiClientErrorCodes.AVV1_CONNECTION_ERROR, cause);
    }

}
