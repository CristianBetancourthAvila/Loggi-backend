package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class EgresoCajaRequestTest {

    private static EgresoCajaRequest model;

    @Test
    @DisplayName("Test for EgresoCajaRequest model")
    void testModelEgresoCajaRequest() {
        model = new EgresoCajaRequest();
        model.setId(1);
        model.setUsuarioId(1);
        model.setUsuarioTerceroId(BigInteger.ONE);
        model.setProgramarPagoId(3);
        model.setMotivoAnulacion("Motivo de Anulación");
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

    @Test
    @DisplayName("Test EgresoCajaRequest AllArgsConstructor")
    void testAllArgsConstructorConstructor() {
        EgresoCajaRequest model = new EgresoCajaRequest(
                1,
                1,
                BigInteger.ONE,
                3,
                "Motivo de Anulación",
                1L
        );
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
