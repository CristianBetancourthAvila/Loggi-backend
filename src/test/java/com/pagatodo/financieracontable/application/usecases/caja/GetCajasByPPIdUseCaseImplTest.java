package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCajasByPPIdUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    private GetCajasByPPIdUseCaseImpl getCajasByPPIdUseCase;

    @BeforeEach
    void setUp() {
        getCajasByPPIdUseCase = new GetCajasByPPIdUseCaseImpl(cajaPort);
    }

    @Test
    @DisplayName("Test for findCajasByPPId method")
    void findCajasByPPId_ValidId_ReturnCajas() throws CajaNotFoundException {
        Integer programarPagoId = 1;
        List<Caja> cajas = Arrays.asList(
                new Caja(),
                new Caja()
        );
        when(cajaPort.findCajasByProgramarPagoid(programarPagoId)).thenReturn(cajas);
        List<Caja> result = getCajasByPPIdUseCase.findCajasByPPId(programarPagoId);
        assertEquals(cajas, result);
    }
}
