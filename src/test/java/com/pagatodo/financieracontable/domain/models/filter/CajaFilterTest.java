package com.pagatodo.financieracontable.domain.models.filter;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaFilterTest {

    @Test
    @DisplayName("Test for CajaFilter model")
    void testModelCajaFilter() {
        CajaFilter filter = new CajaFilter();
        filter.setCodigoCaja("CAJA_001");
        filter.setSptId(BigInteger.valueOf(10));
        filter.setZonaId(BigInteger.valueOf(20));
        filter.setPuntoVentaId(30L);

        assertDoesNotThrow(() -> PropertyTester.test(filter));
    }
}
