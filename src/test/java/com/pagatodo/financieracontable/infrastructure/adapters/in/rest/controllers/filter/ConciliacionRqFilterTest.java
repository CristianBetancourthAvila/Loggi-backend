package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ConciliacionRqFilterTest {

    @Test
    @DisplayName("Test for ConciliacionRqFilter model")
    void testModelConciliacionRqFilter() {
        ConciliacionRqFilter filter = new ConciliacionRqFilter();
        filter.setTipoConciliacion(TipoConciliacion.BANCARIA);
        filter.setAliadoProducto("ALIADO_001");
        filter.setFechaInicial(LocalDate.of(2023, 1, 1));
        filter.setFechaFinal(LocalDate.of(2023, 12, 31));

        assertDoesNotThrow(() -> PropertyTester.test(filter));
    }
}
