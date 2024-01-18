package com.pagatodo.financieracontable.application.exceptions.cartera;

import com.pagatodo.commons.exceptions.NotFoundException;

public class CarteraNotFoundException extends NotFoundException {
    private static final long serialVersionUID = 742513590510496367L;
    public CarteraNotFoundException() {super(CarteraErrorCodes.CARTERA_NOT_FOUND);}
}
