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
class IngresoTest {

    @Test
    @DisplayName("Test for Ingreso model")
    void testModelIngreso(){
        Ingreso model = new Ingreso();
        model.setId(1);
        model.setFechaCreacion(LocalDate.of(2023, 10, 22));
        model.setHoraCreacion(LocalTime.now());
        model.setAperturaCaja(new AperturaCaja());
        model.setParametrizacionConcepto(new ParametrizacionConcepto());
        model.setMedioPagoId(4);
        model.setUsuarioTerceroId(8);
        model.setValorRecibido(25000L);
        model.setMotivoAnulacion("Motivo anulacion");
        model.setObservaciones("Observaciones prueba");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
