package com.pagatodo.financieracontable.domain.models.filter;

import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class AnulacionFilter {

    private TipoMovimiento tipoMovimiento;

    private LocalDate fechaCreacion;

    private EstadoAnulacion estado;
}
