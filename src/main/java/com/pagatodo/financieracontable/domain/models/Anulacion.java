package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class Anulacion {

    private Integer id;

    private TipoMovimiento tipoMovimiento;

    private Ingreso ingreso;

    private EgresoCaja egresoCaja;

    private Integer autorizadorId;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private EstadoAnulacion estado;
}
