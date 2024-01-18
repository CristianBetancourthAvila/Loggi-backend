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
public class RecaudoCarteraRequest {

    private Integer id;

    @NotNull
    private Integer carteraId;

    @NotNull
    private Long aperturaCajaId;

    @NotNull
    private Long valorRecaudo;

    @NotNull
    private Integer medioPagoId;

    private String observaciones;
}
