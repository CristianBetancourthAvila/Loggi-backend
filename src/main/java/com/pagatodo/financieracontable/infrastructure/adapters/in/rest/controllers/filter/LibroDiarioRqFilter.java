package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
public class LibroDiarioRqFilter {
    private LocalDate dateOne;
    private LocalDate dateTwo;
    private BigInteger idCaja;
    private BigInteger idUsuario;
    @NotNull
    private Boolean isAdmin;
    @NotNull
    private Boolean isCloseCaja;
}
