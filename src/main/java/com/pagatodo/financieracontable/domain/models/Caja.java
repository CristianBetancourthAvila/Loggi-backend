package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.financieracontable.domain.models.enums.Estado;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class Caja {

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

    private List<AperturaCaja> aperturaCaja;

    private List<Ingreso> ingreso;

    private List<ProgramarPagoCaja> programarPago;
}
