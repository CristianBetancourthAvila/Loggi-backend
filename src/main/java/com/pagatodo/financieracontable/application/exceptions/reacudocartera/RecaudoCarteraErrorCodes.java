package com.pagatodo.financieracontable.application.exceptions.reacudocartera;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum RecaudoCarteraErrorCodes implements ApiErrorCode {

    INVALID_TRANSACTION("001", "com.pagatodo.financieracontable.application.exceptions.recaudocartera.RecaudoCarteraErrorCodes.INVALID_TRANSACTION");
    private static final String RECAUDO_CARTERA_CONFIG = "RECAUDOCARTERACONFIG";

    private String code;

    private String localizedMessage;

    private RecaudoCarteraErrorCodes(String code, String localizedMessage){
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {return RECAUDO_CARTERA_CONFIG;}

    @Override
    public String getCode() {return code;}

    @Override
    public String getLocalizedMessage() {return localizedMessage;}
}
