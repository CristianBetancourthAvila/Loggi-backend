package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.financieracontable.domain.models.enums.Estado;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProgramarPagoRequest {

    private Integer id;

    private Long parametrizacionConceptoId;

    private BigInteger usuarioTerceroId;

    private Boolean afectacionCartera;

    private Long codigoVendedor;

    private LocalDate fechaAplicacion;

    private String rangoVigencia;

    private Long valor;

    private String observacion;

    private Estado estado;

    @NotNull
    private List<BigInteger> cajaIds;
}
