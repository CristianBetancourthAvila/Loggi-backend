package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CarteraResponse {

    private Integer id;

    private Integer vendedorId;

    private Long saldo;

    private Long ventaDiaLiquidada;

    private Long ventasDia;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

}
