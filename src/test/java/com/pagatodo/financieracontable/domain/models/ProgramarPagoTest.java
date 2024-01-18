package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoTest {

    private ProgramarPago programarPago;

    @Test
    @DisplayName("Test for ProgramarPago model")
    void testModelProgramarPago() {
        programarPago = new ProgramarPago();
        programarPago.setId(1);
        programarPago.setParametrizacionConcepto(new ParametrizacionConcepto());
        programarPago.setUsuarioTerceroId(BigInteger.ONE);
        programarPago.setAfectacionCartera(true);
        programarPago.setFechaCreacion(LocalDate.of(2023, 10, 27));
        programarPago.setCodigoVendedor(123456L);
        programarPago.setFechaAplicacion(LocalDate.of(2023, 11, 1));
        programarPago.setRangoVigencia("01/11/2023 - 30/11/2023");
        programarPago.setValor(5000L);
        programarPago.setObservacion("Pago de prueba");
        programarPago.setEstado(Estado.ACTIVO);
        programarPago.setEgresoCaja(new EgresoCaja());
        assertDoesNotThrow(() -> PropertyTester.test(programarPago));
    }
}
