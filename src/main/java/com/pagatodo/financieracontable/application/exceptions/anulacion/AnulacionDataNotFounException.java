package com.pagatodo.financieracontable.application.exceptions.anulacion;

import com.pagatodo.commons.exceptions.NotFoundException;

public class AnulacionDataNotFounException extends NotFoundException {

    private static final long serialVersionUID = 8528035905104963674L;

    public AnulacionDataNotFounException() {super(AnulacionErrorCodes.ANULACION_NOT_FOUND);}
}
