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
public class EgresoCajaRequest {

    private Integer id;

    private Integer usuarioId;

    private BigInteger usuarioTerceroId;

    private Integer programarPagoId;

    private String motivoAnulacion;

    private Long cajaId;
}
