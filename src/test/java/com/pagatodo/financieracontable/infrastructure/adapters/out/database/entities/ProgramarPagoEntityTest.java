package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoEntityTest {

    @Test
    @DisplayName("Test ProgramarPagoEntity Setters")
    void testModelProgramarPagoEntitySetters() {
        EgresoCajaEntity egresoCajaEntity = new EgresoCajaEntity();
        ProgramarPagoEntity model = new ProgramarPagoEntity();
        model.setId(10);
        model.setParametrizacionConcepto(new ParametrizacionConceptoEntity());
        model.setUsuarioTerceroId(BigInteger.ONE);
        model.setAfectacionCartera(true);
        model.setFechaCreacion(LocalDate.now());
        model.setCodigoVendedor(123456789012345L);
        model.setFechaAplicacion(LocalDate.now().plusDays(1));
        model.setRangoVigencia("Rango de Vigencia");
        model.setValor(1000L);
        model.setObservacion("Observación");
        model.setEstado(Estado.ACTIVO);
        model.setEgresoCaja(egresoCajaEntity);
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

    @Test
    @DisplayName("Test ProgramarPagoEntity AllArgsConstructor")
    void testModelProgramarPagoEntityAllArgsConstructor() {
        EgresoCajaEntity egresoCajaEntity = new EgresoCajaEntity();
        ParametrizacionConceptoEntity parametrizacionConcepto = new ParametrizacionConceptoEntity();
        ProgramarPagoEntity model = new ProgramarPagoEntity(10, parametrizacionConcepto, BigInteger.ONE, true,
                LocalDate.now(), LocalTime.now(), 123456789012345L, LocalDate.now().plusDays(1),
                "Rango de Vigencia", 1000L, "Observación", Estado.ACTIVO, Condicion.NO_PAGADO, egresoCajaEntity);
        Assertions.assertNotNull(model.toString());
    }
}
