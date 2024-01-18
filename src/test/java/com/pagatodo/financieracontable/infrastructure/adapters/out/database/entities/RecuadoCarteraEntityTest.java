package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RecuadoCarteraEntityTest {

    @Test
    @DisplayName("Test for RecaudoCarteraEntity model")
    void testModelRecaudoCarteraEntity(){
        RecaudoCarteraEntity model = new RecaudoCarteraEntity();
        model.setId(1);
        model.setCartera(new CarteraEntity());
        model.setAperturaCaja(new AperturaCajaEntity());
        model.setObservaciones("Observaciones");
        model.setValorRecaudo(1000L);
        model.setMedioPagoId(4);
        model.setFechaCreacion(LocalDate.now());
        model.setHoraCreacion(LocalTime.now());
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
