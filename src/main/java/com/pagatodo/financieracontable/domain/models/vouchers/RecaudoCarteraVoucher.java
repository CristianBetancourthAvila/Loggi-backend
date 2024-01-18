package com.pagatodo.financieracontable.domain.models.vouchers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecaudoCarteraVoucher {

    private Long comprobante;

    private String caja;

    private String zona;

    private String fechaHora;

    private String recibido;

    private String tipoDocumento;

    private String numeroDocumento;

    private String valor;

    private String medioPago;

    private String detalle;

}
