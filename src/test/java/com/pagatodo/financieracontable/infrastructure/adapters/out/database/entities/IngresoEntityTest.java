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
class IngresoEntityTest {

    @Test
    @DisplayName("Test IngresoEntity")
    void testModelIngresoEntity() {
        IngresoEntity model = new IngresoEntity();
        model.setId(1);
        model.setAperturaCaja(new AperturaCajaEntity());
        model.setParametrizacionConcepto(new ParametrizacionConceptoEntity());
        model.setMedioPagoId(4);
        model.setHoraCreacion(LocalTime.now());
        model.setUsuarioTerceroId(5);
        model.setValorRecibido(500000L);
        model.setFechaCreacion(LocalDate.now());
        model.setMotivoAnulacion("Motivo anulacion");
        model.setObservaciones("Entrada de observaciones");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
