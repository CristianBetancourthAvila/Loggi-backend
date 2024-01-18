package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RecaudoCarteraTest {
    @Test
    @DisplayName("Test for RecaudoCartera model")
    void testModelRecaudoCartera(){
        RecaudoCartera model = new RecaudoCartera();
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
