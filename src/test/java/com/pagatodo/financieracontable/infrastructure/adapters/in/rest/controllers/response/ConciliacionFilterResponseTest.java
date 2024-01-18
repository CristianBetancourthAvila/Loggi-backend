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
class ConciliacionFilterResponseTest {

    @Test
    @DisplayName("Test for ConciliacionFilterResponse model")
    void testModelConciliacionFilterResponse() {
        ConciliacionFilterResponse response = new ConciliacionFilterResponse();
        response.setId(1L);
        response.setTipoConciliacion(TipoConciliacion.BANCARIA);
        response.setAliadoProducto("ALIADO_001");
        response.setTotalCruces(10);
        response.setTotalNovedades(5);
        response.setFechaInicial(LocalDate.of(2023, 1, 1));
        response.setFechaFinal(LocalDate.of(2023, 12, 31));
        response.setFechaCreacion(LocalDate.now());
        response.setArchivo("archivo.txt");

        assertDoesNotThrow(() -> PropertyTester.test(response));
    }
}
