package com.pagatodo.financieracontable.domain.models.vouchers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnulacionVoucher {

    private Long comprobante;

    private String comprobanteMovimiento;

    private String concepto;

    private String fechaSolicitud;

    private String fechaHora;

    private String recibido;

    private String tipoDocumento;

    private String numeroDocumento;

    private String valor;

    private String autorizado;

    private String motivo;

    private String autorizador;

    private String identificacion;
}
