package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class AnulacionTest {

    @Test
    @DisplayName("Test for Anulacion model")
    void testModelAnulacion(){
        Anulacion model = new Anulacion();
        model.setId(1);
        model.setIngreso(new Ingreso());
        model.setEstado(EstadoAnulacion.PENDIENTE);
        model.setAutorizadorId(null);
        model.setFechaCreacion(LocalDate.now());
        model.setHoraCreacion(LocalTime.now());
        model.setTipoMovimiento(TipoMovimiento.INGRESO);
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
