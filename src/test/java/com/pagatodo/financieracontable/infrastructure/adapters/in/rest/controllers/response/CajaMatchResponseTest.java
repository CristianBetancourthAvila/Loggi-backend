package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaMatchResponseTest {

    @Test
    @DisplayName("Test for CajaMatchResponse model")
    void testModelCajaMatchResponse() {
        CajaMatchResponse model = new CajaMatchResponse();
        model.setId(BigInteger.ONE);
        model.setCodigoCaja("COD123");
        model.setNombreCaja("Caja de Prueba");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
