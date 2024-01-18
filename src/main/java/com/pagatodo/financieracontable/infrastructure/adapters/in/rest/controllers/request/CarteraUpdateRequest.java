package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarteraUpdateRequest {

    @NotNull
    private Integer id;

    private Long diferenciaExcedenteSaldo;

    private Long diferenciaExcedenteVentaDiaLiquidada;

    private Long diferenciaExcedenteVentasDia;

}
