package com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum AVV1ApiClientErrorCodes implements ApiErrorCode {

    AVV1_NOT_FOUND("001", "com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient.AVV1ApiClientErrorCodes.AVV1_NOT_FOUND"),
    AVV1_CONNECTION_ERROR("002", "com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient.AVV1ApiClientErrorCodes.AVV1_CONNECTION_ERROR"),
    AVV1_BAD_REQUEST("003", "com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient.AVV1ApiClientErrorCodes.AVV1_BAD_REQUEST"),
    AVV1_UNKNOWN_ERROR("004", "com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient.AVV1ApiClientErrorCodes.AVV1_UNKNOWN_ERROR");

    private static final String AVV1APICLIENT = "ADMINISTRAR_VENDEDOR_CLIENT";
    private String code;
    private String localizedMessage;

    private AVV1ApiClientErrorCodes(String code, String localizedMessage) {
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return AVV1APICLIENT;
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
