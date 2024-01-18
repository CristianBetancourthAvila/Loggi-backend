package com.pagatodo.financieracontable.application.exceptions.egresocaja;

import com.pagatodo.commons.exceptions.ApiErrorCode;

public enum EgresoCajaErrorCodes implements ApiErrorCode {

    MOTIVO_ANULACION_NOT_NULL("001","com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaErrorCodes.MOTIVO_ANULACION_NOT_NULL"),
    ERROR_INGRESO("002","com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaErrorCodes.ERROR_INGRESO"),
    EGRESO_CAJA_NOT_FOUND("003","com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaErrorCodes.EGRESO_CAJA_NOT_FOUND"),
    ANULACION_NOT_VALID("004","com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaErrorCodes.ANULACION_NOT_VALID");

    private static final String EGRESO_CAJA = "EGRESOCAJA";
    private String code;
    private String localizedMessage;

    private EgresoCajaErrorCodes(String code, String localizedMessage) {
        this.code = code;
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getPrefix() {
        return EGRESO_CAJA;
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
