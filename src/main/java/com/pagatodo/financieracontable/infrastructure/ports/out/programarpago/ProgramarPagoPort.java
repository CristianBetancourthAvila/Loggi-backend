package com.pagatodo.financieracontable.infrastructure.ports.out.programarpago;

import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.filter.ProgramarPagoFilter;

import java.math.BigInteger;
import java.util.List;

public interface ProgramarPagoPort {

    ProgramarPago save(ProgramarPago programarPago);

    ProgramarPago findById(Integer id);

    List<ProgramarPago> findAllByTerceroId(BigInteger id) throws TerceroNotFoundException;

    List<ProgramarPago> findWithFilter(ProgramarPagoFilter programarPagoFilter);

    ProgramarPago update(ProgramarPago programarPago) throws ProgramarPagoNotFoundException;
    void updateStatus(Integer id,  Estado newStatus);
}
