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
public class CajaUpdateRequest {

    @NotNull
    private BigInteger id;

    private Long montoMaximoPago;

    private Long montoMaximoGiro;

    private Long montoMaximoBeps;

    private Long montoMaximoBbva;

    private Long cantidadPapelBond;

    private Long cantidadPapelTermico;

    private Estado estado;
}
