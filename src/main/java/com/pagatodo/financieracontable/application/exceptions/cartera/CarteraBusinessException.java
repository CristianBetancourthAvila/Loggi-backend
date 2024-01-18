package com.pagatodo.financieracontable.application.exceptions.cartera;


import com.pagatodo.commons.exceptions.BusinessException;

public class CarteraBusinessException extends BusinessException {
    private static final long serialVersionUID = -1584775527390081394L;
    public CarteraBusinessException() {super(CarteraErrorCodes.INVALID_TRANSACTION);}
}
