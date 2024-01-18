package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoUpdateRequestTest {

    @Test
    @DisplayName("Test for ProgramarPagoUpdateRequest model")
    void testModelProgramarPagoUpdateRequest() {
        ProgramarPagoUpdateRequest model = new ProgramarPagoUpdateRequest();
        model.setId(1);
        model.setAfectacionCartera(true);
        model.setCodigoVendedor(12345L);
        model.setFechaAplicacion(LocalDate.now());
        model.setObservacion("Observaciones");
        model.setEstado(Estado.ACTIVO);
        model.setCajaIds(List.of(BigInteger.valueOf(5)));
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
