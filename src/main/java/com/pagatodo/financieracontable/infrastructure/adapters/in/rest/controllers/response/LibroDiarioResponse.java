package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class LibroDiarioResponse {
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
