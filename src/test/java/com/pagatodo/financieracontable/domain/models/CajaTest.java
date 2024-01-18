package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaTest {

    @Test
    @DisplayName("Test for Caja model")
    void testModelCaja(){
        Caja model = new Caja();
        model.setId(BigInteger.valueOf(1));
        model.setPuntoVentaTerminalId(BigInteger.valueOf(2));
        model.setCodigoCaja("CAJA001");
        model.setNombreCaja("Caja de Prueba");
        model.setCodigoDane(12345L);
        model.setFechaCreacion(LocalDate.of(2023, 10, 2));
        model.setHoraCreacion(LocalTime.of(12, 30, 0));
        model.setMontoMaximoPago(10000L);
        model.setMontoMaximoGiro(5000L);
        model.setMontoMaximoBeps(2000L);
        model.setMontoMaximoBbva(1500L);
        model.setCantidadPapelBond(15L);
        model.setCantidadPapelTermico(15L);
        model.setEstado(Estado.ACTIVO);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}