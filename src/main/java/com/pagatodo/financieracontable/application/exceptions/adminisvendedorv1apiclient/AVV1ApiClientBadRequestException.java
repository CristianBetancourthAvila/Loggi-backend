package com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient;

import com.pagatodo.commons.exceptions.BadRequestException;

public class AVV1ApiClientBadRequestException extends BadRequestException {

    private static final long serialVersionUID = 4071956030393395640L;

    public AVV1ApiClientBadRequestException() {
        super(AVV1ApiClientErrorCodes.AVV1_BAD_REQUEST);
    }

}
