package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecaudoCarteraRqVoucher {

    @NotNull
    private Long comprobante;

    @NotNull
    private String caja;

    @NotNull
    private String zona;

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
    private String medioPago;

    @NotNull
    private String detalle;
}
