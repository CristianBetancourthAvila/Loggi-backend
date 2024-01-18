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
public class CarteraRequest {

    private Integer id;

    @NotNull
    private Integer vendedorId;

    @NotNull
    private Long saldo;

}
