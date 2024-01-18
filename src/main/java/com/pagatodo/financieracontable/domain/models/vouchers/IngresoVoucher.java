package com.pagatodo.financieracontable.domain.models.vouchers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngresoVoucher {

    private Long comprobante;

    private String fechaHora;

    private String recibido;

    private String tipoDocumento;

    private String numeroDocumento;

    private String valor;

    private String concepto;

    private String detalle;

    private String observacion;
}
