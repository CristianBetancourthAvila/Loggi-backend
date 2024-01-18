package com.pagatodo.financieracontable.domain.models.filter;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProgramarPagoFilterTest {

    @Test
    @DisplayName("Test for ProgramarPagoFilter model")
    void testModelProgramarPagoFilter() {
        ProgramarPagoFilter filter = new ProgramarPagoFilter();
        filter.setConceptoEgreso(1L);
        filter.setFechaAplicacion(LocalDate.of(2024, 1, 1));
        filter.setCondicion(Condicion.NO_PAGADO);
        filter.setEstado(Estado.ACTIVO);

        assertDoesNotThrow(() -> PropertyTester.test(filter));
    }
}
