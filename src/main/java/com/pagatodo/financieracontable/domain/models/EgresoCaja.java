package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class EgresoCaja {

    private Integer id;

    private BigInteger usuarioTerceroId;

    private Integer usuarioId;

    private ProgramarPago programarPago;

    private String motivoAnulacion;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private AperturaCaja aperturaCaja;
}
