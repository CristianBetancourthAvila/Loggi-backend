package com.pagatodo.financieracontable.domain.models.filter;

import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;

@Getter
@Setter
public class CajaFilter {

    private String codigoCaja;

    private BigInteger sptId;

    private BigInteger zonaId;

    private Long puntoVentaId;
}
