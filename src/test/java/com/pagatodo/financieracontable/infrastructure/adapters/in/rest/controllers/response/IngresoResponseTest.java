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
class IngresoResponseTest {

    @Test
    @DisplayName("Test for IngresoResponse model")
    void testModelIngresoResponse() {
        IngresoResponse model = new IngresoResponse();
        model.setId(1);
        model.setFechaCreacion(LocalDate.now());
        model.setHoraCreacion(LocalTime.now());
        model.setUsuarioTerceroId(4);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
