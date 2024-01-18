package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConciliacionRqFilter {

    private TipoConciliacion tipoConciliacion;

    private String aliadoProducto;

    private LocalDate fechaInicial;

    private LocalDate fechaFinal;
}
