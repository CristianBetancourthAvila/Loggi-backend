package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
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
class GetAllByMatchAndStatusUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    private GetAllByMatchAndStatusUseCaseImpl getAllByMatchAndStatusUseCase;

    @BeforeEach
    void setUp() {
        getAllByMatchAndStatusUseCase = new GetAllByMatchAndStatusUseCaseImpl(cajaPort);
    }

    @Test
    @DisplayName("Test for findAllByMatchesAndStatus method with valid filter text and status")
    void findAllByMatchesAndStatus_ValidFilterAndStatus_ReturnCajas() {
        String filterText = "test";
        Estado status = Estado.ACTIVO;

        List<Caja> cajas = Arrays.asList(
                new Caja(),
                new Caja()
        );

        when(cajaPort.findAllByMatchesAndStatus("TEST", status)).thenReturn(cajas);

        List<Caja> result = getAllByMatchAndStatusUseCase.findAllByMatchesAndStatus(filterText, status);

        assertEquals(cajas, result);
    }

    @Test
    @DisplayName("Test for findAllByMatchesAndStatus method with null filter text")
    void findAllByMatchesAndStatus_NullFilterText_ReturnCajasByTop10() {
        Estado status = Estado.ACTIVO;

        List<Caja> cajasByTop10 = Arrays.asList(
                new Caja(),
                new Caja()
        );

        when(cajaPort.findByTop10AndMatchesAndStatus(null, status)).thenReturn(cajasByTop10);

        List<Caja> result = getAllByMatchAndStatusUseCase.findAllByMatchesAndStatus(null, status);

        assertEquals(cajasByTop10, result);
    }

    @Test
    @DisplayName("Test for findAllByMatchesAndStatus method with short filter text")
    void findAllByMatchesAndStatus_ShortFilterText_ReturnCajasByTop10() {
        Estado status = Estado.ACTIVO;

        List<Caja> cajasByTop10 = Arrays.asList(
                new Caja(),
                new Caja()
        );

        when(cajaPort.findByTop10AndMatchesAndStatus(null, status)).thenReturn(cajasByTop10);

        List<Caja> result = getAllByMatchAndStatusUseCase.findAllByMatchesAndStatus("a", status);

        assertEquals(cajasByTop10, result);
    }
}
