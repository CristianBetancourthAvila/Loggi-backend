package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngresoRqVoucher {

    @NotNull
    private Long comprobante;

    @NotNull
    private String fechaHora;

    @NotNull
    private String recibido;

    @NotNull
    private String tipoDocumento;

    @NotNull
    private String numeroDocumento;

    @NotNull
    private String valor;

    @NotNull
    private String concepto;

    @NotNull
    private String detalle;

    @NotNull
    private String observacion;
}
