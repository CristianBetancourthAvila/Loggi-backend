package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class Traslado {

    private Integer id;

    private Boolean trasladarPremio;

    private String observacionTraslado;

    private String observacionEnvio;

    private String observacionRecepcion;

    private String observacionDiferencia;

    private Integer seriePremio;

    private Caja cajaOrigen;

    private Caja cajaDestino;

    private Ingreso ingreso;

    private EgresoCaja egresoCaja;

    private EstadoTraslado estado;

    private Integer valorDiferencia;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private String numeroBolsa;

}
