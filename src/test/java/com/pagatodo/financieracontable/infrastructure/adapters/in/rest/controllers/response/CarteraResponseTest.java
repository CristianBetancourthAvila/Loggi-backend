package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@ExtendWith(MockitoExtension.class)
class CarteraResponseTest {

    @Test
    @DisplayName("Test CarteraResponse")
    void testModelCCarteraEntity(){
        CarteraResponse model = new CarteraResponse();

        model.setId(1);
        model.setHoraCreacion(LocalTime.now());
        model.setFechaCreacion(LocalDate.now());
        model.setSaldo(500000000L);
        model.setVentasDia(50000L);
        model.setVentaDiaLiquidada(200000L);
        model.setVendedorId(5);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
