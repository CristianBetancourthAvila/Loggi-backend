package com.pagatodo.financieracontable.application.usecases.programarpago;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpagocaja.ProgramarPagoCajaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePPUseCaseImplTest {

    @Mock
    private ProgramarPagoPort programarPagoPort;

    @Mock
    private ParametrizacionConceptoPort parametrizacionConceptoPort;

    @Mock
    private ProgramarPagoCajaPort programarPagoCajaPort;


    @Mock
    private CajaPort cajaPort;

    @InjectMocks
    private CreatePPUseCaseImpl createPPUseCase;

    private ProgramarPago programarPago;

    private Caja caja;
    private ParametrizacionConcepto parametrizacionConcepto;

    @BeforeEach
    void setUp() {
        createPPUseCase = new CreatePPUseCaseImpl(programarPagoPort, cajaPort, programarPagoCajaPort, parametrizacionConceptoPort);

        caja = new Caja();
        caja.setId(BigInteger.ONE);
        caja.setFechaCreacion(LocalDate.now());
        caja.setHoraCreacion(LocalTime.now());

        parametrizacionConcepto = new ParametrizacionConcepto();
        parametrizacionConcepto.setId(1L);
        parametrizacionConcepto.setCodigoConcepto(2L);
        parametrizacionConcepto.setProgramable(true);
        parametrizacionConcepto.setAnulable(false);
        parametrizacionConcepto.setImprimible(true);
        parametrizacionConcepto.setDescripcion("Concepto de pago");
        parametrizacionConcepto.setFechaCreacion(LocalDate.now());

        programarPago = new ProgramarPago();
        programarPago.setId(1);
        programarPago.setUsuarioTerceroId(BigInteger.ONE);
        programarPago.setFechaCreacion(LocalDate.now());
        programarPago.setParametrizacionConcepto(parametrizacionConcepto);
    }

    @Test
    @DisplayName("Create ProgramarPago Successfully")
    void createProgramarPago_Successfully() {
        when(parametrizacionConceptoPort.findById(1L)).thenReturn(parametrizacionConcepto);
        Caja caja1 = new Caja();
        caja1.setId(BigInteger.ONE);
        Caja caja2 = new Caja();
        caja.setId(BigInteger.TWO);
        when(cajaPort.findById(BigInteger.valueOf(1))).thenReturn(caja1);
        when(cajaPort.findById(BigInteger.valueOf(2))).thenReturn(caja2);
        when(programarPagoPort.save(any(ProgramarPago.class))).thenAnswer(invocation -> {
            ProgramarPago argument = invocation.getArgument(0);
            argument.setId(1);
            return argument;
        });
        List<BigInteger> cajaIds = Arrays.asList(BigInteger.valueOf(1), BigInteger.valueOf(2));
        assertDoesNotThrow(() -> {
            ProgramarPago result = createPPUseCase.create(programarPago, cajaIds);
            assertNotNull(result);
            assertEquals(1, result.getId());
        });
    }

    @Test
    @DisplayName("Create ProgramarPago with AperturaCajaNotFoundException")
    void createProgramarPago_WithException() {
        when(parametrizacionConceptoPort.findById(1L)).thenReturn(parametrizacionConcepto);
        when(cajaPort.findById(BigInteger.valueOf(1))).thenReturn(null);
        List<BigInteger> cajaIds = Collections.singletonList(BigInteger.valueOf(1));
        CajaIdNotFoundException exception = assertThrows(CajaIdNotFoundException.class, () -> createPPUseCase.create(programarPago, cajaIds));
        assertNotNull(exception);
    }
}
