package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.financieracontable.domain.models.enums.Estado;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CajaRequest {

    private BigInteger id;

    @NotNull
    private BigInteger puntoVentaTerminalId;

    @NotNull
    private String codigoCaja;

    @NotNull
    private String nombreCaja;

    @NotNull
    private Long codigoDane;

    @NotNull
    private Long montoMaximoPago;

    @NotNull
    private Long montoMaximoGiro;

    @NotNull
    private Long montoMaximoBeps;

    @NotNull
    private Long montoMaximoBbva;

    @NotNull
    private Long cantidadPapelBond;

    @NotNull
    private Long cantidadPapelTermico;

    @NotNull
    private Estado estado;
}
