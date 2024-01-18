package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ConciliacionFilterResponse {

    private Long id;

    private TipoConciliacion tipoConciliacion;

    private String aliadoProducto;

    private Integer totalCruces;

    private Integer totalNovedades;

    private LocalDate fechaInicial;

    private LocalDate fechaFinal;

    private LocalDate fechaCreacion;

    private String archivo;
}
