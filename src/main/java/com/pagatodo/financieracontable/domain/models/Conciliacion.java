package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class Conciliacion {

    private Long id;

    private TipoConciliacion tipoConciliacion;

    private String aliadoProducto;

    private LocalDate fechaInicial;

    private LocalDate fechaFinal;

    private LocalDate fechaCreacion;

    private String archivo;
}
