package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConciliacionRequest {

    private Long id;

    @NotNull
    private TipoConciliacion tipoConciliacion;

    @NotNull
    private String aliadoProducto;

    @NotNull
    private LocalDate fechaInicial;

    @NotNull
    private LocalDate fechaFinal;

    @NotNull
    private String archivo;
}
