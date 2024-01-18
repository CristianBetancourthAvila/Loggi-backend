package com.pagatodo.financieracontable.application.exceptions.parametrizacionconcepto;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum ParametrizacionConceptoErrorCodes implements ApiErrorCode {
	NOT_FOUND("001", "com.pagatodo.financieracontable.application.exceptions.parametrizacionconcepto.ParametrizacionConceptoErrorCodes.PARAMETRIZACION_CONCEPTO_NOT_FOUND");
	
    private static final String PARAMETRIZACION_CONCEPTO = "PARAMETRIZACION_CONCEPTO";
    private String code;
    private String localizedMessage;

    private ParametrizacionConceptoErrorCodes(String code, String localizedMessage) {
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return PARAMETRIZACION_CONCEPTO;
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
