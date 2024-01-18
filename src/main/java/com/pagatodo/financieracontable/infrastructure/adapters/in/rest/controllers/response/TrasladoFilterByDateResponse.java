package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrasladoFilterByDateResponse {

    private Integer consecutivo;

    private String cajaOrigen;

    private String cajaDestino;

    private Integer valorDiferencia;

    private EstadoTraslado estado;

    private String numeroBolsa;
}
