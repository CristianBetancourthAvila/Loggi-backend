package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class IngresoRequestTest {

    @Test
    @DisplayName("Test for IngresoRequest model")
    void testModelIngresoRequest() {
        IngresoRequest model = new IngresoRequest();
        model.setId(4);
        model.setAperturaCajaId(7L);
        model.setParametrizacionConceptoId(4L);
        model.setMedioPagoId(5);
        model.setUsuarioTerceroId(4);
        model.setValorRecibido(25000L);
        model.setMotivoAnulacion("Motivo anulacion");
        model.setObservaciones("Pago extra");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
