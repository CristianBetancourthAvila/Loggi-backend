package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AperturaCajaRequest {

    private BigInteger id;

    private BigInteger usuarioId;

    private BigInteger cajaId;

    private Long saldoInicial;

    private Long billetes;

    private Long monedas;

    private Long premios;

    private Long otros;

    private String observaciones;
}
