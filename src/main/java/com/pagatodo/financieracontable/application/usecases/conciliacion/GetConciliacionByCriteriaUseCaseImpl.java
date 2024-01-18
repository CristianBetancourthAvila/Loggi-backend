package com.pagatodo.financieracontable.application.usecases.conciliacion;

import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionErrorCodes;
import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.in.conciliacion.GetConciliacionByCriteriaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.conciliacion.ConciliacionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConciliacionByCriteriaUseCaseImpl implements GetConciliacionByCriteriaUseCase {

    private static final String INVALID_DATES_FILTER = ConciliacionErrorCodes.INVALID_DATES_FILTER.getLocalizedMessage();

    private final ConciliacionPort conciliacionPort;

    @Transactional(readOnly = true)
    @Override
    public PageModel<List<Conciliacion>> findWithFiler(ConciliacionFilter conciliacionFilter, Integer rowsPerPage, Integer skip) throws ConciliacionBusinessException {
        VisitorValidator.of(conciliacionFilter)
                .and(INVALID_DATES_FILTER, c -> (c.getFechaInicial() != null) == (c.getFechaFinal() != null))
                .and(INVALID_DATES_FILTER, c -> !c.getFechaFinal().isBefore(c.getFechaInicial()))
                .and(INVALID_DATES_FILTER, c -> ChronoUnit.DAYS.between(c.getFechaInicial(), c.getFechaFinal()) <= 31)
                .execute(ConciliacionBusinessException::new);
        return conciliacionPort.findWithFilter(conciliacionFilter, rowsPerPage, skip);
    }
}
