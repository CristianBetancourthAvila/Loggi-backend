package com.pagatodo.financieracontable.application.exceptions.cartera;

import com.pagatodo.commons.exceptions.NotFoundException;

public class CarteraNotFoundByVendedorIdException extends NotFoundException {
    private static final long serialVersionUID = 962813590510496367L;
    public CarteraNotFoundByVendedorIdException() {super(CarteraErrorCodes.CARTERA_NOT_FOUND_BY_VENDEDOR);}
}
