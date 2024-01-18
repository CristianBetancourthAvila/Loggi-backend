package com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections;

import java.math.BigInteger;

public record CajaProjection(
    BigInteger id,
    String codigoCaja,
    String nombreCaja
		) {}
