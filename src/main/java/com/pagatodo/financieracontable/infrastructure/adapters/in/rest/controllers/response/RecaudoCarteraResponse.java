package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;


import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Cartera;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class RecaudoCarteraResponse {

    private Integer id;

    private Cartera cartera;

    private AperturaCaja aperturaCaja;

    private Long valorRecaudo;

    private Integer medioPagoId;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private String observaciones;
}
