package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.enums.Estado;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CajaResponse {

    private BigInteger id;

    private BigInteger puntoVentaTerminalId;

    private String codigoCaja;

    private String nombreCaja;

    private Long codigoDane;

    private LocalDate fechaCreacion;

    private LocalTime horaCreacion;

    private Long montoMaximoPago;

    private Long montoMaximoGiro;

    private Long montoMaximoBeps;

    private Long montoMaximoBbva;

    private Long cantidadPapelBond;

    private Long cantidadPapelTermico;

    private Estado estado;
}
