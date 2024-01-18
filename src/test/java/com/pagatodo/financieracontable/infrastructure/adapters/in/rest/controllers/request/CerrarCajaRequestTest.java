package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CerrarCajaRequestTest {

    @Test
    @DisplayName("Test for CerrarCajaRequest model")
    void testModelCerrarCajaRequest() {
        CerrarCajaRequest model = new CerrarCajaRequest();
        model.setId(1L);
        model.setBilletes(5000L);
        model.setObservaciones("Observacion");
        model.setPremios(5000L);
        model.setOtros(800L);
        model.setSaldoFinal(10800L);
        model.setNumeroBolsa("520");
        model.setAperturaCajaId(4L);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
