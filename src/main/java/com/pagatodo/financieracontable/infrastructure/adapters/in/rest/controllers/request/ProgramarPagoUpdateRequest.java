package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.financieracontable.domain.models.enums.Estado;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProgramarPagoUpdateRequest {

    private Integer id;

    private Boolean afectacionCartera;

    private Long codigoVendedor;

    private LocalDate fechaAplicacion;

    private String observacion;

    private Estado estado;

    private List<BigInteger> cajaIds;
}
