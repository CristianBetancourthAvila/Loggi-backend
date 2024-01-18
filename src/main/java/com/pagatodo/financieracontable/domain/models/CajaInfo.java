package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;


@Getter
@Setter
public class CajaInfo {

    private BigInteger id;

    private String codigoCaja;

    private String nombreCaja;

    private String serial;
}
