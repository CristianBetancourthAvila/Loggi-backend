package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RecaudoCarteraRequestTest {

    @Test
    @DisplayName("Test for RecaudoCarteraRequest model")
    void testModelRecaudoCarteraRequest(){
        RecaudoCarteraRequest model = new RecaudoCarteraRequest();
        model.setId(1);
        model.setCarteraId(1);
        model.setAperturaCajaId(1L);
        model.setObservaciones("Observaciones");
        model.setValorRecaudo(1000L);
        model.setMedioPagoId(4);
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
