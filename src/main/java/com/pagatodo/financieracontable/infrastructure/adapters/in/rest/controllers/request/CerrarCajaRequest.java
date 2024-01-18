package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CerrarCajaRequest {

    private Long id;

    @NotNull
    private Long aperturaCajaId;

    @NotNull
    private String numeroBolsa;

    @NotNull
    private Long saldoFinal;

    @NotNull
    private Long billetes;

    @NotNull
    private Long monedas;

    private Long premios;

    private Long otros;

    private String observaciones;
}
