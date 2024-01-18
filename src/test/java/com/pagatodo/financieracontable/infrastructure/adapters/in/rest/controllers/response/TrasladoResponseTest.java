package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;


import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TrasladoResponseTest {

    @Test
    @DisplayName("Test for TrasladoResponse model")
    void testModelTrasladoResponse() {
        TrasladoResponse model = new TrasladoResponse();
        model.setId(1);
        model.setEstado(EstadoTraslado.PROGRAMADO);
        model.setObservacionTraslado("Observaciones");
        model.setCajaDestinoId(BigInteger.valueOf(100));
        model.setCajaOrigenId(BigInteger.valueOf(250));
        model.setTrasladarPremio(true);
        model.setSeriePremio(520);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

}
