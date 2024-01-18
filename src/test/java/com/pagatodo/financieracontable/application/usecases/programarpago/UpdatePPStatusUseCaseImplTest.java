package com.pagatodo.financieracontable.application.usecases.programarpago;

import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePPStatusUseCaseImplTest {

    @Mock
    private ProgramarPagoPort programarPagoPort;

    @InjectMocks
    private UpdatePPStatusUseCaseImpl updatePPStatusUseCase;

    @Test
    @DisplayName("Update ProgramarPago Status Successfully")
    void updateProgramarPagoStatus_Successfully() {
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setId(1);
        doNothing().when(programarPagoPort).updateStatus(1, Estado.ACTIVO);
        when(programarPagoPort.findById(1)).thenReturn(programarPago);
        assertDoesNotThrow(() -> updatePPStatusUseCase.updateStatus(1));
    }

    @Test
    @DisplayName("Update ProgramarPago Status Exception")
    void updateProgramarPagoStatus_Exception() {
        when(programarPagoPort.findById(1)).thenReturn(null);
        assertThrows(ProgramarPagoNotFoundException.class, () -> updatePPStatusUseCase.updateStatus(1));
    }

    @Test
    @DisplayName("Update ProgramarPago Status Not Allowed")
    void updateProgramarPagoStatus_status_not_allowed() {
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setId(1);
        programarPago.setEstado(Estado.INACTIVO);
        programarPago.setCondicion(Condicion.PAGADO);
        doNothing().when(programarPagoPort).updateStatus(1, Estado.INACTIVO);
        when(programarPagoPort.findById(1)).thenReturn(programarPago);
        assertDoesNotThrow(() -> updatePPStatusUseCase.updateStatus(1));
    }
}
