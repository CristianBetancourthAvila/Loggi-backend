package com.pagatodo.financieracontable.infrastructure.ports.out.traslado;

import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.domain.records.PageModel;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface TrasladoPort {

    Traslado create(Traslado traslado);

    PageModel<List<Traslado>> findTrasladosByDate(LocalDate date,Integer rowsPerPage, Integer skip);

    PageModel<List<Traslado>> findSendReceiveTrasladosByCaja(BigInteger cajaId, Boolean send, Integer rowsPerPage, Integer skip);
}
