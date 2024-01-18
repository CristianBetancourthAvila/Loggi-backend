package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ConciliacionEntityTest {

    @Test
    @DisplayName("Test ConciliacionEntity")
    void testModelConciliacionEntity() {
        ConciliacionEntity model = new ConciliacionEntity();
        model.setId(1L);
        model.setTipoConciliacion(TipoConciliacion.BANCARIA);
        model.setAliadoProducto("Aliado1");
        model.setFechaInicial(LocalDate.now());
        model.setFechaFinal(LocalDate.now().plusDays(7));
        model.setFechaCreacion(LocalDate.now());
        model.setArchivo("archivo_conciliacion.txt");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

    @Test
    @DisplayName("Test ConciliacionEntity AllArgsConstructor")
    void testModelConciliacionEntityAllArgsConstructor() {
        ConciliacionEntity model = new ConciliacionEntity(1L, TipoConciliacion.CARTERA, "Aliado1",
                LocalDate.now(), LocalDate.now().plusDays(7), LocalDate.now(), "archivo_conciliacion.txt");
        Assertions.assertNotNull(model.toString());
    }
}
