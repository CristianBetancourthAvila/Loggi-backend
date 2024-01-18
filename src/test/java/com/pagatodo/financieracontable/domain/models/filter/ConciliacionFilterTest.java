package com.pagatodo.financieracontable.domain.models.filter;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ConciliacionFilterTest {

    @Test
    @DisplayName("Test for ConciliacionFilter model")
    void testModelConciliacionFilter() {
        ConciliacionFilter filter = new ConciliacionFilter();
        filter.setTipoConciliacion(TipoConciliacion.BANCARIA);
        filter.setAliadoProducto("ALIADO_001");
        filter.setFechaInicial(LocalDate.of(2023, 1, 1));
        filter.setFechaFinal(LocalDate.of(2023, 12, 31));

        assertDoesNotThrow(() -> PropertyTester.test(filter));
    }
}
