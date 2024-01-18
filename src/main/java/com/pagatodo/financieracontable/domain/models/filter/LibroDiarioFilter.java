package com.pagatodo.financieracontable.domain.models.filter;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
public class LibroDiarioFilter {
    private LocalDate dateOne;
    private LocalDate dateTwo;
    private BigInteger idCaja;
    private BigInteger idUsuario;
    private Boolean isAdmin;
    private Boolean isCloseCaja;
}
