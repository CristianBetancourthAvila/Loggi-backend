package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetByDateMensualUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    private GetByDateMensualUseCaseImpl getByDateMensualUseCase;

    @BeforeEach
    void setUp() {
        getByDateMensualUseCase = new GetByDateMensualUseCaseImpl(cajaPort);
    }

    @Test
    @DisplayName("Test for findByDateMensual method")
    void findByDateMensual_ValidDate_ReturnFileInforme() throws CajaNotFoundException, IOException, JRException {
        LocalDate date = LocalDate.of(2023, 4, 15);
        List<Caja> cajas = Arrays.asList(
                new Caja(),
                new Caja()
        );
        when(cajaPort.findAllByDateYearAndMonth(2023, 4)).thenReturn(cajas);

        FileReport result = getByDateMensualUseCase.findByDateMensual(date);

        Assertions.assertNotNull(result.getFile());
        Assertions.assertNotEquals(0, result.getFile().length());
    }

}
