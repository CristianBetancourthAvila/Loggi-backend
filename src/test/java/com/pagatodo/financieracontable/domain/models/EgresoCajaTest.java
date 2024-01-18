package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class EgresoCajaTest {

    private EgresoCaja egresoCaja;

    @Test
    @DisplayName("Test for EgresoCaja model")
    void testModelEgresoCaja() {
        egresoCaja = new EgresoCaja();
        egresoCaja.setId(1);
        egresoCaja.setUsuarioTerceroId(BigInteger.ONE);
        egresoCaja.setProgramarPago(new ProgramarPago());
        egresoCaja.setMotivoAnulacion("Anulación por motivo");
        egresoCaja.setFechaCreacion(LocalDate.of(2023, 10, 27));
        assertDoesNotThrow(() -> PropertyTester.test(egresoCaja));
    }
}
