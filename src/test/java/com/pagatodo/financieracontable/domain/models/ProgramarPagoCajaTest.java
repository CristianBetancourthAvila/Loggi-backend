package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoCajaTest {

    private ProgramarPagoCaja programarPagoCaja;

    @Test
    @DisplayName("Test for ProgramarPagoCaja model")
    void testModelProgramarPagoCaja() {
        programarPagoCaja = new ProgramarPagoCaja();
        programarPagoCaja.setId(1);
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setId(101);
        programarPagoCaja.setProgramarPago(programarPago);
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        programarPagoCaja.setCaja(caja);
        assertDoesNotThrow(() -> PropertyTester.test(programarPagoCaja));
    }
}
