package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TrasladoRequestTest {

    @Test
    @DisplayName("Test for TrasladoRequest model")
    void testModelTrasladoRequest(){
        TrasladoRequest model = new TrasladoRequest();
        model.setId(1);
        model.setObservacionTraslado("Observaciones");
        model.setCajaOrigenId(BigInteger.TWO);
        model.setCajaDestinoId(BigInteger.ONE);
        model.setSeriePremio(123);
        model.setTrasladarPremio(true);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
