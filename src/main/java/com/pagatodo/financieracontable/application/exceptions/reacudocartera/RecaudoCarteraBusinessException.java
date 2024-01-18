package com.pagatodo.financieracontable.application.exceptions.reacudocartera;

import com.pagatodo.commons.exceptions.BusinessException;

public class RecaudoCarteraBusinessException extends BusinessException {

    private static final long serialVersionUID = -7546671527380081394L;
    public RecaudoCarteraBusinessException() {super(RecaudoCarteraErrorCodes.INVALID_TRANSACTION);}
}
