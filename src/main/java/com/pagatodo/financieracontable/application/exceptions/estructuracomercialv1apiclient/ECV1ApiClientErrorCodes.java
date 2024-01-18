package com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum ECV1ApiClientErrorCodes implements ApiErrorCode {

    ECV1_NOT_FOUND("001", "com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientErrorCodes.ECV1_NOT_FOUND"),
    ECV1_CONNECTION_ERROR("002", "com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientErrorCodes.ECV1_CONNECTION_ERROR"),
    ECV1_BAD_REQUEST("003", "com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientErrorCodes.ECV1_BAD_REQUEST"),
    ECV1_UNKNOWN_ERROR("004", "com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientErrorCodes.ECV1_UNKNOWN_ERROR"),
    ECV1_TERMINAL_NOT_FOUND("004", "com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientErrorCodes.ECV1_TERMINAL_NOT_FOUND");
    private static final String ECV1APICLIENT = "ESTRUCTURA_COMERCIAL_CLIENT";
    private String code;
    private String localizedMessage;

    private ECV1ApiClientErrorCodes(String code, String localizedMessage) {
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return ECV1APICLIENT;
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
