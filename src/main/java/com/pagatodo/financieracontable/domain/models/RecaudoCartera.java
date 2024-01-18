package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class RecaudoCartera {

    private Integer id;

    private Cartera cartera;

    private AperturaCaja aperturaCaja;

    private Long valorRecaudo;

    private Integer medioPagoId;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private String observaciones;
}
