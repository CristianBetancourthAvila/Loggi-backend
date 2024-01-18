package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class AperturaCajaTest {

    @Test
    @DisplayName("Test for AperturaCaja model")
    void testModelAperturaCaja(){
        AperturaCaja model = new AperturaCaja();
        model.setId(1L);
        model.setUsuarioId(BigInteger.valueOf(2));
        Caja caja = new Caja();
        model.setCaja(caja);
        model.setEstado(true);
        model.setFechaApertura(LocalDate.of(2023, 10, 2));
        model.setHoraApertura(LocalTime.of(9, 0, 0));
        model.setSaldoAnterior(10000L);
        model.setSaldoInicial(10500L);
        model.setBilletes(8000L);
        model.setMonedas(2500L);
        model.setPremios(300L);
        model.setOtros(200L);
        model.setObservaciones("Apertura de caja de prueba");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
