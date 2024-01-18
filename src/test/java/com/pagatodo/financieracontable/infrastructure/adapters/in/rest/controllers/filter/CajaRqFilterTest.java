package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaRqFilterTest {

    @Test
    @DisplayName("Test for CajaRqFilter model")
    void testModelCajaRqFilter() {
        CajaRqFilter model = new CajaRqFilter();
        model.setCodigoCaja("CAJA001");
        model.setSptId(BigInteger.valueOf(123));
        model.setZonaId(BigInteger.valueOf(456));
        model.setPuntoVentaId(1L);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
