package com.pagatodo.financieracontable.application.usecases.egresocaja;

import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaNotFoundException;
import com.pagatodo.financieracontable.application.usecases.commons.CreateAnulacionUtils;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja.EgresoCajaPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateMotivoECUseCaseImplTest {

    @Mock
    private EgresoCajaPort egresoCajaPort;

    @Mock
    private CreateAnulacionUtils createAnulacionUtils;

    @InjectMocks
    private UpdateMotivoECUseCaseImpl updateMotivoECUseCase;

    private static EgresoCaja egresoCaja;

    @BeforeAll
    static void configInitial() {
        egresoCaja = new EgresoCaja();
        ProgramarPago programarPago = new ProgramarPago();
        ParametrizacionConcepto parametrizacionConcepto = new ParametrizacionConcepto();
        parametrizacionConcepto.setAnulable(false);
        programarPago.setParametrizacionConcepto(parametrizacionConcepto);
        egresoCaja.setId(1);
        egresoCaja.setProgramarPago(programarPago);
    }

    @Test
    @DisplayName("Update Motivo for EgresoCaja Test with Exception")
    void updateMotivoEgresoCajaTestException() throws EgresoCajaNotFoundException {
        int id = 1;
        String motivoAnulacion = "Motivo de anulación";
        EgresoCaja egresoCaja1 = new EgresoCaja();
        egresoCaja1.setMotivoAnulacion(motivoAnulacion);
        when(egresoCajaPort.findById(id)).thenReturn(egresoCaja);
        assertThrows(EgresoCajaBusinessException.class,
                () -> updateMotivoECUseCase.updateMotivo(id, egresoCaja1));
        verify(egresoCajaPort, times(1)).findById(id);
    }

    @Test
    @DisplayName("Update Motivo for EgresoCaja Test")
    void updateMotivoEgresoCajaTest() throws EgresoCajaNotFoundException {
        int id = 1;
        String motivoAnulacion = "Motivo de anulación";
        EgresoCaja egresoCaja1 = new EgresoCaja();
        egresoCaja1.setMotivoAnulacion(motivoAnulacion);
        egresoCaja.getProgramarPago().getParametrizacionConcepto().setAnulable(true);
        when(egresoCajaPort.findById(id)).thenReturn(egresoCaja);
        assertDoesNotThrow(() -> updateMotivoECUseCase.updateMotivo(id, egresoCaja1));
        verify(egresoCajaPort, times(1)).findById(id);
    }
}
