package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class CajaInfoResponse {

    private BigInteger id;

    private String codigoCaja;

    private String nombreCaja;

    private String serial;
}
