package com.pagatodo.financieracontable.application.usecases.programarpago;

import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.filter.ProgramarPagoFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPPByCriteriaUseCaseImplTest {

    @Mock
    private ProgramarPagoPort programarPagoPort;

    @InjectMocks
    private GetPPByCriteriaUseCaseImpl getPPByCriteriaUseCase;

    @BeforeEach
    void setUp() {
        getPPByCriteriaUseCase = new GetPPByCriteriaUseCaseImpl(programarPagoPort);
    }

    @Test
    @DisplayName("Find ProgramarPago by Criteria and Paginate Successfully")
    void findProgramarPagoByCriteriaAndPaginate_Successfully() {
        ProgramarPagoFilter filter = new ProgramarPagoFilter();
        int rowsPerPage = 10;
        int skip = 0;
        List<ProgramarPago> programarPagosList = Collections.singletonList(new ProgramarPago());
        when(programarPagoPort.findWithFilter(any(ProgramarPagoFilter.class))).thenReturn(programarPagosList);
        PageModel<List<ProgramarPago>> result = getPPByCriteriaUseCase.findWithFiler(filter, rowsPerPage, skip);
        assertEquals(programarPagosList, result.data());
        assertEquals(BigInteger.valueOf(programarPagosList.size()), result.total());
    }
}
