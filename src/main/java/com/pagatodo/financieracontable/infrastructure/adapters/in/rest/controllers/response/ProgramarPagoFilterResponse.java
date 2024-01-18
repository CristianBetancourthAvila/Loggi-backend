package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ProgramarPagoFilterResponse {

    private Integer id;

    private ParametrizacionConcepto parametrizacionConcepto;

    private BigInteger usuarioTerceroId;

    private Boolean afectacionCartera;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private Long codigoVendedor;

    private LocalDate fechaAplicacion;

    private String rangoVigencia;

    private Long valor;

    private String observacion;

    private Estado estado;

    private Condicion condicion;

    private EgresoCaja egresoCaja;

    private List<Caja> cajaList;
}
