package com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient;

import com.pagatodo.commons.exceptions.BadRequestException;

public class ECV1ApiClientBadRequestException extends BadRequestException {

    private static final long serialVersionUID = 4071956030393395640L;

    public ECV1ApiClientBadRequestException() {
        super(ECV1ApiClientErrorCodes.ECV1_BAD_REQUEST);
    }

}
