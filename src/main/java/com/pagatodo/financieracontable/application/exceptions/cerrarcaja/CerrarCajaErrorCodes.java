package com.pagatodo.financieracontable.application.exceptions.cerrarcaja;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum CerrarCajaErrorCodes implements ApiErrorCode {

    INVALID_TRANSACTION("001", "com.pagatodo.financieracontable.application.exceptions.cerrarcaja.CerrarCajaErrorCodes.INVALID_TRANSACTION"),
    INVALID_AMOUNT("002", "com.pagatodo.financieracontable.application.exceptions.cerrarcaja.CerrarCajaErrorCodes.INVALID_AMOUNT");

    private static final String CERRAR_CAJA_CONFIG = "CERRARCAJACONFIG";

    private String code;

    private String localizedMessage;

    private CerrarCajaErrorCodes(String code, String localizedMessage){
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {return CERRAR_CAJA_CONFIG;}

    @Override
    public String getCode() {return code;}

    @Override
    public String getLocalizedMessage() {return localizedMessage;}
}
