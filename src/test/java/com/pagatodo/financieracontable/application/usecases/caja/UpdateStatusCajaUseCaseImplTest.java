package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateStatusCajaUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    @InjectMocks
    private UpdateStatusCajaUseCaseImpl useCase;

    private Caja caja;

    @BeforeEach
    void setUp() {
        caja = new Caja();
        caja.setId(BigInteger.valueOf(1));
        caja.setEstado(Estado.ACTIVO);
    }

    @Test
    @DisplayName("Test for updateStatus method")
    void updateStatus_CajaExists_ActiveToInactive() throws CajaIdNotFoundException {
        BigInteger id = BigInteger.valueOf(1);
        when(cajaPort.findById(id)).thenReturn(caja);
        useCase.updateStatus(id);
        Mockito.verify(cajaPort, Mockito.times(1)).updateStatus(id, Estado.INACTIVO);
    }

    @Test
    @DisplayName("Test for updateStatus method when Caja is not found")
    void updateStatus_CajaNotFound_ThrowNotFoundException() {
        BigInteger id = BigInteger.valueOf(2);
        when(cajaPort.findById(id)).thenReturn(null);
        assertThrows(CajaIdNotFoundException.class, () -> useCase.updateStatus(id));
    }
}
