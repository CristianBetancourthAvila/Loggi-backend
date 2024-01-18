package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AperturaCaja {

    private Long id;

    private BigInteger usuarioId;

    private Caja caja;

    private Boolean estado;

    private LocalDate fechaApertura;

    private LocalTime horaApertura;

    private Long saldoAnterior;

    private Long saldoInicial;

    private Long billetes;

    private Long monedas;

    private Long premios;

    private Long otros;

    private String observaciones;
}
