package com.pagatodo.financieracontable.application.exceptions.egresocaja;

import com.pagatodo.commons.exceptions.BusinessException;

public class EgresoCajaBusinessException extends BusinessException {

    private static final long serialVersionUID = -1584770967360081394L;

    public EgresoCajaBusinessException() {
        super(EgresoCajaErrorCodes.ERROR_INGRESO);
    }
}
