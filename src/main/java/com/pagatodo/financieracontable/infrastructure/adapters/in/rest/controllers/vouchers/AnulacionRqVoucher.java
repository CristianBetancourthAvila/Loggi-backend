package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnulacionRqVoucher {
    @NotNull
    private Long comprobante;

    @NotNull
    private String comprobanteMovimiento;

    @NotNull
    private String concepto;

    @NotNull
    private String fechaSolicitud;

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
    private String autorizado;

    @NotNull
    private String motivo;

    @NotNull
    private String autorizador;

    @NotNull
    private String identificacion;
}
