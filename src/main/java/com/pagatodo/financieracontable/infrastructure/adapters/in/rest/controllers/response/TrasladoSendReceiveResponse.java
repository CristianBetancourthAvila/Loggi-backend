package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrasladoSendReceiveResponse {

    private Integer consecutivo;

    private String cajaOrigenCajero;

    private Long valor;

    private Integer comprobante;

    private EstadoTraslado estado;
}
