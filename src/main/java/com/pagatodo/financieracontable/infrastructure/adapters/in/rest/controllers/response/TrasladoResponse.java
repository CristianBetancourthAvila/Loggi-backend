package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class TrasladoResponse {

    private Integer id;

    private Boolean trasladarPremio;

    private String observacionTraslado;

    private Integer seriePremio;

    private BigInteger cajaOrigenId;

    private BigInteger cajaDestinoId;

    private EstadoTraslado estado;
}
