package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CerrarCajaTest {

    @Test
    @DisplayName("Test for CerrarCaja model")
    void testModelCerrarCaja(){
        CerrarCaja model = new CerrarCaja();
        model.setFechaCierre(LocalDate.of(2023, 10, 2));
        model.setHoraCierre(LocalTime.of(9, 0, 0));
        model.setSaldoFinal(10000L);
        model.setNumeroBolsa("123");
        model.setAperturaCaja(new AperturaCaja());
        model.setBilletes(8000L);
        model.setMonedas(2500L);
        model.setPremios(300L);
        model.setOtros(200L);
        model.setObservaciones("Cierre de caja de prueba");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
