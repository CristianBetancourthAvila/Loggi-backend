package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class EgresoCajaResponse {

    private Integer id;

    private BigInteger usuarioTerceroId;

    private Integer usuarioId;

    private ProgramarPago programarPago;

    private String motivoAnulacion;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private AperturaCaja aperturaCaja;
}
