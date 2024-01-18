package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ProgramarPagoRQFilter {

    private Long conceptoEgreso;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAplicacion;

    private Condicion condicion;

    private Estado estado;
}
