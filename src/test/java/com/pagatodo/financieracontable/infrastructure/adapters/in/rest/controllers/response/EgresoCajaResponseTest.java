package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class EgresoCajaResponseTest {

    private static EgresoCajaResponse model;

    @Test
    @DisplayName("Test for EgresoCajaResponse model")
    void testModelEgresoCajaResponse() {
        model = new EgresoCajaResponse();
        model.setId(1);
        model.setUsuarioTerceroId(BigInteger.ONE);
        model.setProgramarPago(new ProgramarPago());
        model.setMotivoAnulacion("Motivo de Anulación");
        model.setFechaCreacion(LocalDate.of(2023, 10, 2));
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
