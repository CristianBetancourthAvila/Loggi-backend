package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCajaUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    @InjectMocks
    private UpdateCajaUseCaseImpl useCase;

    private Caja existingCaja;

    @BeforeEach
    void setUp() {
        existingCaja = new Caja();
        existingCaja.setId(BigInteger.valueOf(1));
        existingCaja.setCodigoCaja("CAJA001");
    }

    @Test
    @DisplayName("Test for updating an existing Caja")
    void updateExistingCaja_Success() throws NotFoundException {
        Caja updatedCaja = new Caja();
        updatedCaja.setId(BigInteger.valueOf(1));
        updatedCaja.setNombreCaja("Updated Caja Name");
        when(cajaPort.update(updatedCaja)).thenReturn(updatedCaja);
        Caja result = useCase.update(updatedCaja);
        assertNotNull(result);
        assertEquals("Updated Caja Name", result.getNombreCaja());
    }
}
