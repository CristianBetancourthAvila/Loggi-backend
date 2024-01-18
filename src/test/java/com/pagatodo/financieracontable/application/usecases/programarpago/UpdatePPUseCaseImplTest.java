package com.pagatodo.financieracontable.application.usecases.programarpago;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
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
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePPUseCaseImplTest {

    @Mock
    private ProgramarPagoPort programarPagoPort;

    @Mock
    private CajaPort cajaPort;

    @Mock
    private ProgramarPagoCajaPort programarPagoCajaPort;

    @InjectMocks
    private UpdatePPUseCaseImpl updatePPUseCase;

    private static Caja caja;

    @BeforeEach
    void setUp() {
        updatePPUseCase = new UpdatePPUseCaseImpl(programarPagoPort, cajaPort, programarPagoCajaPort);
        caja = new Caja();
        caja.setId(BigInteger.ONE);
        caja.setPuntoVentaTerminalId(BigInteger.TWO);
        caja.setCodigoCaja("CAJA001");
        caja.setNombreCaja("Caja de Prueba");
        caja.setCodigoDane(123456L);
        caja.setMontoMaximoPago(10000L);
        caja.setMontoMaximoGiro(5000L);
        caja.setMontoMaximoBeps(2000L);
        caja.setMontoMaximoBbva(3000L);
        caja.setCantidadPapelBond(500L);
        caja.setCantidadPapelTermico(1000L);
        caja.setEstado(Estado.ACTIVO);
    }


    @Test
    @DisplayName("Update ProgramarPago Successfully")
    void updateProgramarPago_Successfully() throws ProgramarPagoNotFoundException, CajaNotFoundException, CajaIdNotFoundException {
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setId(1);
        List<BigInteger> cajaIds = Collections.singletonList(BigInteger.valueOf(2));
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        when(cajaPort.findById(BigInteger.TWO)).thenReturn(caja);
        List<Caja> cajaList = Collections.singletonList(caja);
        when(cajaPort.findCajasByProgramarPagoid(programarPago.getId())).thenReturn(cajaList);
        when(programarPagoPort.findById(programarPago.getId())).thenReturn(programarPago);
        when(programarPagoPort.update(programarPago)).thenReturn(programarPago);
        ProgramarPago updatedProgramarPago = updatePPUseCase.update(programarPago, cajaIds);
        assertEquals(programarPago, updatedProgramarPago);
    }

    @Test
    @DisplayName("Update ProgramarPago Exception")
    void updateProgramarPago_Exception() throws CajaNotFoundException {
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setId(1);
        List<BigInteger> cajaIds = Collections.singletonList(BigInteger.valueOf(2));
        when(cajaPort.findById(BigInteger.TWO)).thenReturn(null);
        List<Caja> cajaList = Collections.singletonList(caja);
        when(cajaPort.findCajasByProgramarPagoid(programarPago.getId())).thenReturn(cajaList);
        when(programarPagoPort.findById(programarPago.getId())).thenReturn(programarPago);
        assertThrows(CajaIdNotFoundException.class, () -> updatePPUseCase.update(programarPago, cajaIds));
    }

    @Test
    @DisplayName("Update ProgramarPago Status Not Allowed")
    void updateProgramarPago_Status_Not_Allowed() throws ProgramarPagoNotFoundException, CajaNotFoundException, CajaIdNotFoundException {
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setId(1);
        programarPago.setEstado(Estado.INACTIVO);
        programarPago.setCondicion(Condicion.PAGADO);
        List<BigInteger> cajaIds = Collections.singletonList(BigInteger.valueOf(2));
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        when(cajaPort.findById(BigInteger.TWO)).thenReturn(caja);
        List<Caja> cajaList = Collections.singletonList(caja);
        when(cajaPort.findCajasByProgramarPagoid(programarPago.getId())).thenReturn(cajaList);
        when(programarPagoPort.findById(programarPago.getId())).thenReturn(programarPago);
        when(programarPagoPort.update(programarPago)).thenReturn(programarPago);
        ProgramarPago updatedProgramarPago = updatePPUseCase.update(programarPago, cajaIds);
        assertEquals(programarPago, updatedProgramarPago);
    }
}
