package com.pagatodo.financieracontable.application.exceptions.traslado;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum TrasladoErrorCodes implements ApiErrorCode {
    ERROR_TRASLADO("001","com.pagatodo.financieracontable.application.exceptions.traslado.TrasladoErrorCodes.ERROR_TRASLADO"),
    FIELD_SERIE_PREMIO_REQUIRED("002", "com.pagatodo.financieracontable.application.exceptions.traslado.TrasladoErrorCodes.FIELD_SERIE_PREMIO_REQUIRED");

    private static final String TRASLADO_CONFIG = "TRASLADOCONFIG";

    private String code;

    private String localizedMessage;

    private TrasladoErrorCodes(String code, String localizedMessage){
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return TRASLADO_CONFIG;
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
