package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrasladoRequest {

    private Integer id;

    @NotNull
    private Boolean trasladarPremio;

    private String observacionTraslado;

    private Integer seriePremio;

    @NotNull
    private BigInteger cajaOrigenId;

    @NotNull
    private BigInteger cajaDestinoId;
}
