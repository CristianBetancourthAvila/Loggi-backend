package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoResponseTest {

    @Test
    @DisplayName("Test for ProgramarPagoResponse model")
    void testModelProgramarPagoResponse() {
        ProgramarPagoResponse model = new ProgramarPagoResponse();
        model.setId(1);
        model.setParametrizacionConcepto(new ParametrizacionConcepto());
        model.setUsuarioTerceroId(BigInteger.ONE);
        model.setAfectacionCartera(true);
        model.setFechaCreacion(LocalDate.now());
        model.setCodigoVendedor(12345L);
        model.setFechaAplicacion(LocalDate.now());
        model.setRangoVigencia("Rango de vigencia");
        model.setValor(5000L);
        model.setObservacion("Observación");
        model.setEstado(Estado.ACTIVO);
        model.setEgresoCaja(new EgresoCaja());
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
