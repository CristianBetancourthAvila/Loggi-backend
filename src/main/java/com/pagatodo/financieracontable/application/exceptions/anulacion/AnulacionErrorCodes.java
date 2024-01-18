package com.pagatodo.financieracontable.application.exceptions.anulacion;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum AnulacionErrorCodes implements ApiErrorCode {
    AT_LEAST_INGRESO_OR_EGRESO("001", "com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionErrorCodes.AT_LEAST_INGRESO_OR_EGRESO"),

    ANULACION_NOT_FOUND("002", "com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionErrorCodes.ANULACION_NOT_FOUND"),

    ERROR_ANULACION("003", " com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionErrorCodes.ERROR_ANULACION");

    private static final String ANULACION_CONFIG = "ANULACIONCONFIG";

    private String code;

    private String localizedMessage;

    private AnulacionErrorCodes(String code, String localizedMessage){
        this.code = code;
        this.localizedMessage = localizedMessage;
    }
    @Override
    public String getPrefix() { return ANULACION_CONFIG; }

    @Override
    public String getCode() { return code; }

    @Override
    public String getLocalizedMessage() { return localizedMessage; }
}
