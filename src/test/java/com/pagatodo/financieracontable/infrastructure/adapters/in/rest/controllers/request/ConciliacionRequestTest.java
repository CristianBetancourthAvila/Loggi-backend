package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ConciliacionRequestTest {

    private static ConciliacionRequest model;

    @Test
    @DisplayName("Test for ConciliacionRequest model")
    void testModelConciliacionRequest() {
        model = new ConciliacionRequest();
        model.setId(1L);
        model.setTipoConciliacion(null);
        model.setAliadoProducto("Aliado 123");
        model.setFechaInicial(LocalDate.of(2023, 10, 1));
        model.setFechaFinal(LocalDate.of(2023, 10, 10));
        model.setArchivo("archivo_conciliacion.txt");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
