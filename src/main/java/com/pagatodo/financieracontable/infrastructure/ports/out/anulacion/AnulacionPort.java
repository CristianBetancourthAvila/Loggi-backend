package com.pagatodo.financieracontable.infrastructure.ports.out.anulacion;

import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.domain.records.PageModel;

import java.time.LocalDate;
import java.util.List;

public interface AnulacionPort {

    Anulacion create(Anulacion anulacion);

    PageModel<List<Anulacion>> findAnulacionesByCriteria(TipoMovimiento movementType, LocalDate date, EstadoAnulacion status,Integer rowsPerPage, Integer skip);

    void updateAuthorizerUser(Integer id, Integer userId, EstadoAnulacion status);

    Anulacion findById(Integer id);
}
