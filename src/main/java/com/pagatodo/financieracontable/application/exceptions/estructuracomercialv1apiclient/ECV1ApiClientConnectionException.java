package com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient;

import com.pagatodo.commons.exceptions.ConnectionException;

public class ECV1ApiClientConnectionException extends ConnectionException {

    private static final long serialVersionUID = 2642333535847059143L;

    public ECV1ApiClientConnectionException(Throwable cause) {
        super(ECV1ApiClientErrorCodes.ECV1_CONNECTION_ERROR, cause);
    }

}
