package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CarteraRequestTest {

    @Test
    @DisplayName("Test for CarteraRequest model")
    void testModelCarteraRequest() {
       CarteraRequest model = new CarteraRequest();
       model.setId(1);
       model.setSaldo(200000L);
       model.setVendedorId(4);

       assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
