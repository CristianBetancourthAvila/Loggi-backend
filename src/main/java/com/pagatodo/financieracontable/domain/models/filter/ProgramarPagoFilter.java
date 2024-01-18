package com.pagatodo.financieracontable.domain.models.filter;

import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ProgramarPagoFilter {

    private Long conceptoEgreso;

    private LocalDate fechaAplicacion;

    private Condicion condicion;

    private Estado estado;
}
