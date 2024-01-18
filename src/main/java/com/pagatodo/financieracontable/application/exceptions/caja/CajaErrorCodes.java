package com.pagatodo.financieracontable.application.exceptions.caja;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum CajaErrorCodes implements ApiErrorCode {

    CAJA_ID_NOT_FOUND("001", "com.pagatodo.financieracontable.application.exceptions.caja.CajaErrorCodes.CAJA_ID_NOT_FOUND"),
    CAJA_NOT_FOUND("002", "com.pagatodo.financieracontable.application.exceptions.caja.CajaErrorCodes.CAJA_NOT_FOUND");
    private static final String CAJA = "CAJA";
    private String code;
    private String localizedMessage;

    private CajaErrorCodes(String code, String localizedMessage) {
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return CAJA;
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
