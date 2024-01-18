package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ConciliacionTest {

    @Test
    @DisplayName("Test for Conciliacion model")
    void testModelConciliacion() {
        Conciliacion model = new Conciliacion();
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
