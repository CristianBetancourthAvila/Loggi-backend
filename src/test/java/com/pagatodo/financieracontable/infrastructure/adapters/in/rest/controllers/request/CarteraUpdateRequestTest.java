package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CarteraUpdateRequestTest {

    @Test
    @DisplayName("Test for CarteraUpdateRequest model")
    void testModelCarteraUpdateRequest() {
        CarteraUpdateRequest model = new CarteraUpdateRequest();
        model.setId(1);
        model.setDiferenciaExcedenteSaldo(500000L);
        model.setDiferenciaExcedenteVentasDia(20000L);
        model.setDiferenciaExcedenteVentaDiaLiquidada(60000L);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
