package com.pagatodo.financieracontable.application.usecases.conciliacion;

import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.out.conciliacion.ConciliacionPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetConciliacionByCriteriaUseCaseImplTest {

    @Mock
    private ConciliacionPort conciliacionPort;

    @InjectMocks
    private GetConciliacionByCriteriaUseCaseImpl getConciliacionByCriteriaUseCase;

    @Test
    @DisplayName("Find Conciliacion By Criteria Successfully")
    void findConciliacionByCriteria_Successfully() throws ConciliacionBusinessException {
        ConciliacionFilter conciliacionFilter = new ConciliacionFilter();
        conciliacionFilter.setFechaInicial(LocalDate.of(2024,01,01));
        conciliacionFilter.setFechaFinal(LocalDate.of(2024,01,02));
        int rowsPerPage = 10;
        int skip = 0;

        List<Conciliacion> conciliaciones = new ArrayList<>();

        PageModel<List<Conciliacion>> pageModel = new PageModel<>(conciliaciones, BigInteger.valueOf(conciliaciones.size()));

        when(conciliacionPort.findWithFilter(any(ConciliacionFilter.class), any(Integer.class), any(Integer.class)))
                .thenReturn(pageModel);

        PageModel<List<Conciliacion>> result = getConciliacionByCriteriaUseCase.findWithFiler(conciliacionFilter, rowsPerPage, skip);

        assertEquals(conciliaciones, result.data());
    }
}
