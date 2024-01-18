package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;


import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class AnulacionRqFilterTest {

    @Test
    @DisplayName("Test for AnulacionRqFilter model")
    void testModelAnulacionRqFilter() {
        AnulacionRqFilter model = new AnulacionRqFilter();
        model.setEstado(EstadoAnulacion.PENDIENTE);
        model.setTipoMovimiento(TipoMovimiento.INGRESO);
        model.setFechaCreacion(LocalDate.now());

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
