package com.pagatodo.financieracontable.application.exceptions.traslado;

import com.pagatodo.commons.exceptions.BusinessException;

public class TrasladoBusinessException extends BusinessException {

    private static final long serialVersionUID = -2564770917490081394L;

    public TrasladoBusinessException() {super(TrasladoErrorCodes.ERROR_TRASLADO);}
}
