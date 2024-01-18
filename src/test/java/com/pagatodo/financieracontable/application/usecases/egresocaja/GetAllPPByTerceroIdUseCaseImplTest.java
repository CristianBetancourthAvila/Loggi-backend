package com.pagatodo.financieracontable.application.usecases.egresocaja;

import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPPByTerceroIdUseCaseImplTest {

    private GetAllPPByTerceroIdUseCaseImpl getAllPPByTerceroIdUseCaseImpl;
    private ProgramarPagoPort programarPagoPort;

    @BeforeEach
    void setUp() {
        programarPagoPort = mock(ProgramarPagoPort.class);
        getAllPPByTerceroIdUseCaseImpl = new GetAllPPByTerceroIdUseCaseImpl(programarPagoPort);
    }

    @Test
    @DisplayName("Get All ProgramarPago by Tercero Id - Success")
    void getAllProgramarPagoByTerceroId_Success() throws TerceroNotFoundException {
        BigInteger terceroId = BigInteger.ONE;
        int rowsPerPage = 10;
        int skip = 0;
        List<ProgramarPago> programarPagos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ProgramarPago programarPago = new ProgramarPago();
            programarPago.setId(i);
            programarPagos.add(programarPago);
        }
        when(programarPagoPort.findAllByTerceroId(any())).thenReturn(programarPagos);
        PageModel<List<ProgramarPago>> result = getAllPPByTerceroIdUseCaseImpl.findByTerceroId(terceroId, rowsPerPage, skip);
        assertNotNull(result);
        assertEquals(rowsPerPage, result.data().size());
        assertEquals(BigInteger.valueOf(20), result.total());
    }

    @Test
    @DisplayName("Get All ProgramarPago by Tercero Id - Empty Result")
    void getAllProgramarPagoByTerceroId_EmptyResult() throws TerceroNotFoundException {
        BigInteger terceroId = BigInteger.ONE;
        int rowsPerPage = 10;
        int skip = 0;
        when(programarPagoPort.findAllByTerceroId(any())).thenReturn(new ArrayList<>());
        PageModel<List<ProgramarPago>> result = getAllPPByTerceroIdUseCaseImpl.findByTerceroId(terceroId, rowsPerPage, skip);
        assertNotNull(result);
        assertTrue(result.data().isEmpty());
        assertEquals(BigInteger.ZERO, result.total());
    }
}
