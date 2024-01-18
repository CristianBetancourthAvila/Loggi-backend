package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TrasladoFilterByDateResponseTest {

    @Test
    @DisplayName("Test TrasladoFilterByDateResponse model")
    void testModelTrasladoFilterByDateResponse(){
        TrasladoFilterByDateResponse model = new TrasladoFilterByDateResponse();
        model.setConsecutivo(1);
        model.setEstado(EstadoTraslado.EN_PROCESO);
        model.setValorDiferencia(15200);
        model.setNumeroBolsa("28251");
        model.setCajaOrigen("1002-CJ1500");
        model.setCajaDestino("5002-CJ1501");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
