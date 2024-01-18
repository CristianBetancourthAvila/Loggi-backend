package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ConciliacionResponseTest {

    @Test
    @DisplayName("Test for ConciliacionResponse model")
    void testModelConciliacionResponse() {
        ConciliacionResponse model = new ConciliacionResponse();
        model.setId(1L);
        model.setTipoConciliacion(TipoConciliacion.BANCARIA);
        model.setAliadoProducto("Aliado 123");
        model.setFechaInicial(LocalDate.of(2023, 10, 1));
        model.setFechaFinal(LocalDate.of(2023, 10, 10));
        model.setFechaCreacion(LocalDate.now());
        model.setArchivo("archivo_conciliacion.txt");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
