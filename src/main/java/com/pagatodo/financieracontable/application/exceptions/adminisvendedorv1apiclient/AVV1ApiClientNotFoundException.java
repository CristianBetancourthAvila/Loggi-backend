package com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient;

import com.pagatodo.commons.exceptions.NotFoundException;

public class AVV1ApiClientNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 7618035995107963675L;
    public AVV1ApiClientNotFoundException() {
        super(AVV1ApiClientErrorCodes.AVV1_NOT_FOUND);
    }
}
