package com.pagatodo.financieracontable.application.exceptions.programarpago;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum ProgramarPagoErrorCodes implements ApiErrorCode {

    TERCERO_NOT_FOUND("001","com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoErrorCodes.TERCERO_NOT_FOUND"),

    PROGRAMAR_PAGO_NOT_FOUND("002","com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoErrorCodes.PROGRAMAR_PAGO_NOT_FOUND");
    private static final String PROGRAMAR_PAGO = "PROGRAMARPAGO";
    private String code;
    private String localizedMessage;

    private ProgramarPagoErrorCodes(String code, String localizedMessage) {
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return PROGRAMAR_PAGO;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLocalizedMessage() {
        return localizedMessage;
    }
}

