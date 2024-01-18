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
class ProgramarPagoRequestTest {

    @Test
    @DisplayName("Test for ProgramarPagoRequest model")
    void testModelProgramarPagoRequest() {
        ProgramarPagoRequest model = new ProgramarPagoRequest();
        model.setId(1);
        model.setParametrizacionConceptoId(2L);
        model.setUsuarioTerceroId(BigInteger.ONE);
        model.setAfectacionCartera(true);
        model.setCodigoVendedor(12345L);
        model.setFechaAplicacion(LocalDate.now());
        model.setRangoVigencia("Rango de vigencia");
        model.setValor(50000L);
        model.setObservacion("Observaciones");
        model.setEstado(Estado.ACTIVO);
        model.setCajaIds(List.of(BigInteger.valueOf(5)));

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
