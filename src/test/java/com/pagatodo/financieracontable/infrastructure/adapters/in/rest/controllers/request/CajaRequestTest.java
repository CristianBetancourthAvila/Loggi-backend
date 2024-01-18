package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaRequestTest {

    private static CajaRequest model;

    @Test
    @DisplayName("Test for CajaRequest model")
    void testModelCajaRequest() {
        model = new CajaRequest();
        model.setId(null);
        model.setPuntoVentaTerminalId(null);
        model.setCodigoCaja("CAJA001");
        model.setNombreCaja("Caja de Prueba");
        model.setCodigoDane(null);
        model.setMontoMaximoPago(10000L);
        model.setMontoMaximoGiro(5000L);
        model.setMontoMaximoBeps(2000L);
        model.setMontoMaximoBbva(1500L);
        model.setCantidadPapelBond(15L);
        model.setCantidadPapelTermico(15L);
        model.setEstado(Estado.ACTIVO);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

    @Test
    @DisplayName("Test CajaRequest AllArgsConstructor")
    void testAllArgsConstructorConstructor() {
        CajaRequest model = new CajaRequest(
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                "CAJA001",
                "Caja de Prueba",
                12345L,
                10000L,
                5000L,
                2000L,
                1500L,
                15L,
                15L,
                Estado.ACTIVO
        );

        Assertions.assertNotNull(model);
    }
}
