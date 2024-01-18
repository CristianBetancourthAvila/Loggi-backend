package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LibroDiario {

    private List<Ingreso> ingresos;
    private List<EgresoCaja> egresos;
    private Integer quantityIncomes;
    private Integer quantityCashOuts;
    private Long totalIncomes;
    private Long totalCashOuts;
    private Long previousBalance;
    private Long dailyNet;
    private Long newBalance;
    private BigInteger usuarioId;
    private String cajaName;
    private LocalDate dateSummary;
    private Integer quantityUserDateOne;
    private Integer quantityUserDateTwo;
    private Integer quantityCajaDateOne;
    private Integer quantityCajaDateTwo;
    private Integer quantityTotalCaja;
    private Integer quantityTotalUser;
}
