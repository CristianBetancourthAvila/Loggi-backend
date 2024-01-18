package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngresoRequest {

    private Integer id;

    private Long parametrizacionConceptoId;

    private Long aperturaCajaId;

    private Integer medioPagoId;

    private Integer usuarioTerceroId;

    private Long valorRecibido;

    private String observaciones;

    private String motivoAnulacion;

}
