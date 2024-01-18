package com.pagatodo.financieracontable.application.exceptions.financieracontable;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum FinancieraContableErrorCodes implements ApiErrorCode {

    UNKNOWN_FINANCIERA_CONTABLE_ERROR("001", "com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes.UNKNOWN_FINANCIERA_CONTABLE_ERROR"),

    INACTIVATED_CAJA("002","com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes.INACTIVATED_CAJA"),

    NOT_ASSIGNED_CAJA("003", "com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes.NOT_ASSIGNED_CAJA"),

    CAJA_IS_NOT_OPEN("004", "com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableErrorCodes.CAJA_IS_NOT_OPEN");


    private static final String FINANCIERA_CONTABLE = "FINANCIERACONTABLE";
    private String code;
    private String localizedMessage;

    private FinancieraContableErrorCodes(String code, String localizedMessage) {
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return FINANCIERA_CONTABLE;
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
