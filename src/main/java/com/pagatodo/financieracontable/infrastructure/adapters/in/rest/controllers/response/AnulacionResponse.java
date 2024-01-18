package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AnulacionResponse {

    private Integer id;

    private TipoMovimiento tipo;

    private Long identificacion;

    private Long codigoConcepto;

    private String nombreConcepto;

    private String tipoConcepto;

    private Integer titularDocumento;

    private LocalDate fechaSolicitud;

    private LocalTime horaSolicitud;

    private Long valor;

    private String motivo;

    private Integer autorizado;

    private EstadoAnulacion estadoAnulacion;

    private Integer movimientoId;

}
