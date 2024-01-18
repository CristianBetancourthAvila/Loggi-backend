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
class AperturaCajaResponseTest {

    @Test
    @DisplayName("Test for AperturaCajaResponse model")
    void testModelAperturaCajaResponse() {
        AperturaCajaResponse model = new AperturaCajaResponse();
        model.setNombreCaja("BAZAR ALSACIA3");
        model.setCodigoCaja("CJ00002");
        model.setAbierta(false);
        model.setAperturaCajaId(324L);
        model.setSaldoInicial(20000L);
        model.setFechaCreacion(LocalDate.now());
        model.setHoraCreacion(LocalTime.now());

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
