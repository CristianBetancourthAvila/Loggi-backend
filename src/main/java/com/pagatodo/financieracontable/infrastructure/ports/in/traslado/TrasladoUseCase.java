package com.pagatodo.financieracontable.infrastructure.ports.in.traslado;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.domain.records.PageModel;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface TrasladoUseCase {

    Traslado create(Traslado traslado) throws BusinessException;

    PageModel<List<Traslado>> findTrasladosByDate(LocalDate date, Integer rowsPerPage, Integer skip);

    PageModel<List<Traslado>> findSendReceiveTrasladosByCaja(BigInteger cajaId, Boolean send, Integer rowsPerPage, Integer skip);
}
