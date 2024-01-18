package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaInfoResponseTest {

    @Test
    @DisplayName("Test for CajaInfoResponse model")
    void testModelCajaInfoResponse() {
        CajaInfoResponse cajaInfoResponse = new CajaInfoResponse();
        cajaInfoResponse.setId(BigInteger.valueOf(1));
        cajaInfoResponse.setCodigoCaja("CAJA001");
        cajaInfoResponse.setNombreCaja("Caja de Prueba");
        cajaInfoResponse.setSerial("ABC123");

        assertDoesNotThrow(() -> PropertyTester.test(cajaInfoResponse));
    }
}
