package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

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
class AnulacionEntityTest {

    @Test
    @DisplayName("Test AnulacionEntity")
    void testModelAnulacionEntity(){
       AnulacionEntity model = new AnulacionEntity();
       model.setId(1);
       model.setFechaCreacion(LocalDate.now());
       model.setHoraCreacion(LocalTime.now());
       model.setIngreso(new IngresoEntity());
       model.setEstado(EstadoAnulacion.PENDIENTE);
       model.setAutorizadorId(null);
       model.setTipoMovimiento(TipoMovimiento.INGRESO);
       assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
