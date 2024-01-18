package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class Ingreso {

    private Integer id;

    private ParametrizacionConcepto parametrizacionConcepto;

    private AperturaCaja aperturaCaja;

    private Integer medioPagoId;

    private Integer usuarioTerceroId;

    private Long valorRecibido;

    private String observaciones;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private String motivoAnulacion;
}
