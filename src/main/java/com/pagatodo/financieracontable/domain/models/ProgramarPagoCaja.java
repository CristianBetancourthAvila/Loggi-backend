package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramarPagoCaja {

    private Integer id;

    private ProgramarPago programarPago;

    private Caja caja;
}
