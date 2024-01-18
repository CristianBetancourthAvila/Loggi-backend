package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetByDateSemanalUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetByDateSemanalUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    private GetByDateSemanalUseCase getByDateSemanalUseCase;

    @Mock
    private GetByDateMensualUseCaseImpl getByDateMensualUseCase ;

    @BeforeEach
    void setUp() {
        getByDateSemanalUseCase = new GetByDateSemanalUseCaseImpl(cajaPort,getByDateMensualUseCase);
    }

    @Test
    @DisplayName("Test for findByDateSemanal method")
    void findByDateSemanal_ReturnsListOfCajas() throws CajaNotFoundException, JRException, FileNotFoundException {
        LocalDate from = LocalDate.of(2023, 10, 1);
        LocalDate to = LocalDate.of(2023, 10, 7);
        FileReport fileReport = new FileReport();
        fileReport.setFile("");
        List<Caja> expectedCajas = Arrays.asList(
                new Caja(),
                new Caja()
        );
        Mockito.when(cajaPort.findAllByDateBetween(from, to)).thenReturn(expectedCajas);
        Mockito.when(getByDateMensualUseCase.generateReport(expectedCajas)).thenReturn(fileReport);
        FileReport result = getByDateSemanalUseCase.findByDateSemanal(from, to);
        Assertions.assertNotNull(result.getFile());
    }
}
