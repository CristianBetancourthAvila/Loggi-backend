package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaUpdateRequestTest {

    @Test
    @DisplayName("Test for CajaUpdateRequest model")
    void testModelCajaUpdateRequest() {
        CajaUpdateRequest model = new CajaUpdateRequest();
        model.setId(BigInteger.valueOf(1));
        model.setMontoMaximoPago(10000L);
        model.setMontoMaximoGiro(5000L);
        model.setMontoMaximoBeps(2000L);
        model.setMontoMaximoBbva(3000L);
        model.setCantidadPapelBond(500L);
        model.setCantidadPapelTermico(1000L);
        model.setEstado(Estado.ACTIVO);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

    @Test
    @DisplayName("Test CajaUpdateRequest AllArgsConstructor")
    void testAllArgsConstructorConstructor() {
        CajaUpdateRequest model = new CajaUpdateRequest(
                BigInteger.valueOf(1),
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
