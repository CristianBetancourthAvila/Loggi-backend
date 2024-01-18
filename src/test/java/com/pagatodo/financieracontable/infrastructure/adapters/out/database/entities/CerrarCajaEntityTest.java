package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;


import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CerrarCajaEntityTest {

    @Test
    @DisplayName("Test CerrarCajaEntity")
    void testModelCerrarCajaEntity() {

        CerrarCajaEntity model = new CerrarCajaEntity();
        model.setFechaCierre(LocalDate.of(2023, 10, 2));
        model.setHoraCierre(LocalTime.of(9, 0, 0));
        model.setSaldoFinal(10000L);
        model.setNumeroBolsa("123");
        model.setAperturaCaja(new AperturaCajaEntity());
        model.setBilletes(8000L);
        model.setMonedas(2500L);
        model.setPremios(300L);
        model.setOtros(200L);
        model.setObservaciones("Cierre de caja de prueba");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }


}
