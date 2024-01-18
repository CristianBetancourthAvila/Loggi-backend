package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaInfoTest {

    @Test
    @DisplayName("Test for CajaInfo model")
    void testModelCajaInfo() {
        CajaInfo cajaInfo = new CajaInfo();
        cajaInfo.setId(BigInteger.valueOf(1));
        cajaInfo.setCodigoCaja("CAJA001");
        cajaInfo.setNombreCaja("Caja de Prueba");
        cajaInfo.setSerial("ABC123");

        assertDoesNotThrow(() -> PropertyTester.test(cajaInfo));
    }
}
