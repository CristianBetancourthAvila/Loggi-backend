package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnulacionRqFilter {

    private TipoMovimiento tipoMovimiento;

    private LocalDate fechaCreacion;

    private EstadoAnulacion estado;
}
