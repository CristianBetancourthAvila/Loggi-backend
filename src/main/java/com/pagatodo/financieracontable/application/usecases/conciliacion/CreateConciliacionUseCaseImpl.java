package com.pagatodo.financieracontable.application.usecases.conciliacion;

import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionErrorCodes;
import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.infrastructure.ports.in.conciliacion.CreateConciliacionUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.conciliacion.ConciliacionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CreateConciliacionUseCaseImpl implements CreateConciliacionUseCase {

    private static final String INVALID_DATES_SAVE = ConciliacionErrorCodes.INVALID_DATES_SAVE.getLocalizedMessage();

    private final ConciliacionPort conciliacionPort;

    @Transactional
    @Override
    public Conciliacion create(Conciliacion conciliacion) throws ConciliacionBusinessException {
        VisitorValidator.of(conciliacion)
                .and(INVALID_DATES_SAVE, c -> !c.getFechaFinal().isBefore(c.getFechaInicial()))
                .and(INVALID_DATES_SAVE, c -> ChronoUnit.DAYS.between(c.getFechaInicial(), c.getFechaFinal()) <= 31)
                .execute(ConciliacionBusinessException::new);
        conciliacion.setId(null);
        conciliacion.setFechaCreacion(LocalDate.now());
        return conciliacionPort.save(conciliacion);
    }
}
