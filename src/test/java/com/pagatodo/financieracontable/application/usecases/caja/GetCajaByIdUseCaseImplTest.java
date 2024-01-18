package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCajaByIdUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    @InjectMocks
    private GetCajaByIdUseCaseImpl getCajaByIdUseCase;

    @Test
    @DisplayName("Test for findById method with existing ID")
    void findById_ExistingId_ReturnCaja() {
        BigInteger existingId = BigInteger.valueOf(123);
        Caja caja = new Caja();
        when(cajaPort.findById(existingId)).thenReturn(caja);
        Caja result = getCajaByIdUseCase.findById(existingId);
        assertNotNull(result);
        assertEquals(caja, result);
        verify(cajaPort, times(1)).findById(existingId);
    }

    @Test
    @DisplayName("Test for findById method with non-existing ID")
    void findById_NonExistingId_ReturnNull() {
        BigInteger nonExistingId = BigInteger.valueOf(456);
        when(cajaPort.findById(nonExistingId)).thenReturn(null);
        Caja result = getCajaByIdUseCase.findById(nonExistingId);
        assertNull(result);
        verify(cajaPort, times(1)).findById(nonExistingId);
    }
}
