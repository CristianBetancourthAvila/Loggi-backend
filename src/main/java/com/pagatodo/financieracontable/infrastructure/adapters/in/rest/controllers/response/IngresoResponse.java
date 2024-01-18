package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class IngresoResponse {

    private Integer id;

    private LocalDate fechaCreacion;

    private Integer usuarioTerceroId;

    private LocalTime horaCreacion;
}
