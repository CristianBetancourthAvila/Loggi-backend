package com.pagatodo.financieracontable.application.exceptions.librodiario;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum LibroDiarioErrorCodes implements ApiErrorCode {

    INVALID_TRANSACTION("001", "com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioErrorCodes.INVALID_TRANSACTION"),
    INVALID_DATES("002", "com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioErrorCodes.INVALID_DATES"),
    INVALID_FILTER("003", "com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioErrorCodes.INVALID_FILTER");

    private static final String LIBRO_DIARIO = "LIBRO_DIARIO";

    private String code;

    private String localizedMessage;

    private LibroDiarioErrorCodes(String code, String localizedMessage){
        this.code = code;
        this.localizedMessage = localizedMessage;
    }
    @Override
    public String getPrefix() { return LIBRO_DIARIO;}

    @Override
    public String getCode() { return code;}

    @Override
    public String getLocalizedMessage() {return localizedMessage;}
}

