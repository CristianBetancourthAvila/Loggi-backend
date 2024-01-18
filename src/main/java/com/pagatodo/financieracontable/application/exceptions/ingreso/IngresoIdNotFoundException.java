package com.pagatodo.financieracontable.application.exceptions.ingreso;

import com.pagatodo.commons.exceptions.NotFoundException;

public class IngresoIdNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 7628035905104963674L;

    public IngresoIdNotFoundException() {super(IngresoErrorCodes.INGRESO_ID_NOT_FOUND);}
}
