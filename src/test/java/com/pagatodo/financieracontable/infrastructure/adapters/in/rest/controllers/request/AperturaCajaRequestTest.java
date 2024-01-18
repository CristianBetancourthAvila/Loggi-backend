package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class AperturaCajaRequestTest {

    @Test
    @DisplayName("Test for AperturaCajaRequest model")
    void testModelAperturaCajaRequest() {
        AperturaCajaRequest model = new AperturaCajaRequest();
        model.setId(BigInteger.valueOf(1));
        model.setUsuarioId(BigInteger.valueOf(1));
        model.setCajaId(BigInteger.valueOf(7));
        model.setSaldoInicial(50000L);
        model.setBilletes(30000L);
        model.setMonedas(10000L);
        model.setPremios(5000L);
        model.setOtros(5000L);
        model.setObservaciones("Pago extra");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
