package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;

@Getter
@Setter
public class CajaRqFilter {

    private String codigoCaja;

    private BigInteger sptId;

    private BigInteger zonaId;

    private Long puntoVentaId;
}
