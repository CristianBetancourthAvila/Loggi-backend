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
class AperturaCajaEntityTest {
    @Test
    @DisplayName("Test AperturaCajaEntity")
    void testModelAperturaCajaEntity() {
        AperturaCajaEntity model = new AperturaCajaEntity();
        model.setId(1L);
        model.setUsuarioId(100L);
        model.setCaja(new CajaEntity());
        model.setEstado(true);
        model.setFechaApertura(LocalDate.now());
        model.setHoraApertura(LocalTime.of(9, 0));
        model.setSaldoAnterior(1000L);
        model.setSaldoInicial(1000L);
        model.setBilletes(500L);
        model.setMonedas(500L);
        model.setPremios(0L);
        model.setOtros(0L);
        model.setObservaciones("Prueba de observaciones");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

}
