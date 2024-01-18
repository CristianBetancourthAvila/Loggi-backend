package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoRQFilterTest {

    @Test
    @DisplayName("Test for ProgramarPagoRQFilter model")
    void testModelProgramarPagoRQFilter() {
        ProgramarPagoRQFilter model = new ProgramarPagoRQFilter();
        model.setConceptoEgreso(1L);
        model.setFechaAplicacion(LocalDate.now());
        model.setCondicion(Condicion.NO_PAGADO);
        model.setEstado(Estado.ACTIVO);
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
