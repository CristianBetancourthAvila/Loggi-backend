package com.pagatodo.financieracontable.application.exceptions.aperturacaja;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum AperturaCajaErrorCodes implements ApiErrorCode {

    OBSERVACIONES_FIELD_REQUIRED("001", "com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes.OBSERVACIONES_FIELD_REQUIRED"),

    INVALID_AMOUNT("002", "com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes.INVALID_AMOUNT"),

    INVALID_TRANSACTION("003", "com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes.INVALID_TRANSACTION"),

    CAJA_ALREADY_OPEN("004 ", "com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes.CAJA_ALREADY_OPEN"),

    CAJA_DOES_NOT_MATCH("005", "com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes.CAJA_DOES_NOT_MATCH"),

    APERTURA_CAJA_NOT_FOUND("006", "com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes.APERTURA_CAJA_NOT_FOUND");

    private static final String APERTURA_CAJA_CONFIG = "APERTURACAJACONFIG";

    private String code;

    private String localizedMessage;

    private AperturaCajaErrorCodes(String code, String localizedMessage){
       this.code = code;
       this.localizedMessage = localizedMessage;
    }
    @Override
    public String getPrefix() { return APERTURA_CAJA_CONFIG;}

    @Override
    public String getCode() { return code;}

    @Override
    public String getLocalizedMessage() {return localizedMessage;}
}
