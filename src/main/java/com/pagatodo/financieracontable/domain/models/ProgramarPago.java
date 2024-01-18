package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ProgramarPago {

    private Integer id;

    private ParametrizacionConcepto parametrizacionConcepto;

    private BigInteger usuarioTerceroId;

    private Boolean afectacionCartera;

    private LocalDate fechaCreacion;

    private Long codigoVendedor;

    private LocalDate fechaAplicacion;

    private LocalTime horaCreacion;

    private String rangoVigencia;

    private Long valor;

    private String observacion;

    private Estado estado;

    private Condicion condicion;

    private EgresoCaja egresoCaja;
}
