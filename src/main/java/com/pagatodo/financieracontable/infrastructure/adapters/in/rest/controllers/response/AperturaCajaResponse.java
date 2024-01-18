package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AperturaCajaResponse {

    Long saldoInicial;

    Long aperturaCajaId;

    LocalDate fechaCreacion;

    LocalTime horaCreacion;

    String nombreCaja;

    String codigoCaja;

    Boolean abierta;
}
