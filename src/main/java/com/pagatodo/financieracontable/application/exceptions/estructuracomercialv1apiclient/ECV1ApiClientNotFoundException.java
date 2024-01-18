package com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient;

import com.pagatodo.commons.exceptions.ApiErrorCode;
import com.pagatodo.commons.exceptions.NotFoundException;

public class ECV1ApiClientNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 7618035995107963675L;

    public ECV1ApiClientNotFoundException() {
        super(ECV1ApiClientErrorCodes.ECV1_NOT_FOUND);
    }

    public ECV1ApiClientNotFoundException(ApiErrorCode message) {
        super(message);
    }
}
