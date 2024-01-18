package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CerrarCaja {

    private Long id;

    private AperturaCaja aperturaCaja;

    private String numeroBolsa;

    private LocalDate fechaCierre;

    private LocalTime horaCierre;

    private Long saldoFinal;

    private Long billetes;

    private Long monedas;

    private Long premios;

    private Long otros;

    private String observaciones;
}
