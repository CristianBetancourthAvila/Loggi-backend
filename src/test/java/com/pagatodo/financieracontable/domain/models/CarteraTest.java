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
class CarteraTest {
    @Test
    @DisplayName("Test for Cartera model")
    void testModelCartera(){
        Cartera model = new Cartera();
        model.setId(1);
        model.setFechaCreacion(LocalDate.now());
        model.setHoraCreacion(LocalTime.now());
        model.setVentasDia(200000L);
        model.setVentaDiaLiquidada(10000L);
        model.setVendedorId(4);
        model.setSaldo(4000000L);
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
