package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Cartera;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RecaudoCarteraResponseTest {

    @Test
    @DisplayName("Test for RecaudoCarteraResponse model")
    void testModelRecaudoCarteraResponse(){
        RecaudoCarteraResponse model = new RecaudoCarteraResponse();
        model.setId(1);
        Cartera cartera = new Cartera();
        model.setCartera(cartera);
        AperturaCaja aperturaCaja = new AperturaCaja();
        model.setAperturaCaja(aperturaCaja);
        model.setObservaciones("Observaciones");
        model.setValorRecaudo(1000L);
        model.setMedioPagoId(4);
        model.setFechaCreacion(LocalDate.now());
        model.setHoraCreacion(LocalTime.now());
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
