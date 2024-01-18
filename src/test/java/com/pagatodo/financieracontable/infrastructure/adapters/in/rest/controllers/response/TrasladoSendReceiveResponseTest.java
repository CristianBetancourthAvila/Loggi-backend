package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TrasladoSendReceiveResponseTest {

    @Test
    @DisplayName("Test TrasladoResponseFilterByDate model")
    void testModelTrasladoResponseFilterByDate(){
        TrasladoSendReceiveResponse model =  new TrasladoSendReceiveResponse();
        model.setCajaOrigenCajero("1002 - CJ4125");
        model.setEstado(EstadoTraslado.EN_PROCESO);
        model.setValor(25000L);
        model.setConsecutivo(1);
        model.setComprobante(4);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
