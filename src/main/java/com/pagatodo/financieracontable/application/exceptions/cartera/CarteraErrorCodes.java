package com.pagatodo.financieracontable.application.exceptions.cartera;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum CarteraErrorCodes implements ApiErrorCode {

    INVALID_TRANSACTION("001", "com.pagatodo.financieracontable.application.exceptions.cartera.CarteraErrorCodes.INVALID_TRANSACTION"),

    BALANCE_LESS_THAN_ZERO("002", "com.pagatodo.financieracontable.application.exceptions.cartera.CarteraErrorCodes.BALANCE_LESS_THAN_ZERO"),

    CARTERA_NOT_FOUND_BY_VENDEDOR("003", "com.pagatodo.financieracontable.application.exceptions.cartera.CarteraErrorCodes.CARTERA_NOT_FOUND_BY_VENDEDOR"),

    CARTERA_NOT_FOUND("004", "com.pagatodo.financieracontable.application.exceptions.cartera.CarteraErrorCodes.CARTERA_NOT_FOUND");

    private static final String CARTERA_CONFIG = "CARTERACONFIG";

    private String code;

    private String localizedMessage;

    private CarteraErrorCodes(String code, String localizedMessage){
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {return CARTERA_CONFIG;}

    @Override
    public String getCode() {return code;}

    @Override
    public String getLocalizedMessage() {return localizedMessage;}
}
