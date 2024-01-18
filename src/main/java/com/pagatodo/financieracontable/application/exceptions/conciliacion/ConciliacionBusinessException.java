package com.pagatodo.financieracontable.application.exceptions.conciliacion;


import com.pagatodo.commons.exceptions.BusinessException;

public class ConciliacionBusinessException extends BusinessException {
    private static final long serialVersionUID = -1584775527390081394L;
    public ConciliacionBusinessException() {super(ConciliacionErrorCodes.INVALID_TRANSACTION);}
}
