package com.pagatodo.financieracontable.application.exceptions.conciliacion;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum ConciliacionErrorCodes implements ApiErrorCode {

    INVALID_TRANSACTION("001", "com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionErrorCodes.INVALID_TRANSACTION"),

    INVALID_DATES_SAVE("002", "com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionErrorCodes.INVALID_DATES_SAVE"),

    INVALID_DATES_FILTER("003", "com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionErrorCodes.INVALID_DATES_FILTER");

    private static final String CONCILIACIONCONFIG = "CONCILIACION_CONFIG";

    private String code;

    private String localizedMessage;

    private ConciliacionErrorCodes(String code, String localizedMessage){
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {return CONCILIACIONCONFIG;}

    @Override
    public String getCode() {return code;}

    @Override
    public String getLocalizedMessage() {return localizedMessage;}
}
