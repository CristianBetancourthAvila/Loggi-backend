package com.pagatodo.financieracontable.application.exceptions.ingreso;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum IngresoErrorCodes implements ApiErrorCode {

    ERROR_INGRESO("001", "com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoErrorCodes.ERROR_INGRESO"),
    INGRESO_ID_NOT_FOUND("002","com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoErrorCodes.INGRESO_ID_NOT_FOUND"),

    INGRESO_NOT_NULLABLE("003","com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoErrorCodes.INGRESO_NOT_NULLABLE");


    private static final String INGRESO_CONFIG = "INGRESOCONFIG";

    private String code;

    private String localizedMessage;

    private IngresoErrorCodes(String code, String localizedMessage){
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {return INGRESO_CONFIG;}

    @Override
    public String getCode() {return code;}

    @Override
    public String getLocalizedMessage() {return localizedMessage;}
}
