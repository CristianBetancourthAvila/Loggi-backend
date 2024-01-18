package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
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
class EgresoCajaEntityTest {

    @Test
    @DisplayName("Test EgresoCajaEntity Setters")
    void testModelEgresoCajaEntitySetters() {
        ProgramarPagoEntity programarPagoEntity = new ProgramarPagoEntity();
        EgresoCajaEntity model = new EgresoCajaEntity();
        model.setId(10);
        model.setUsuarioTerceroId(BigInteger.ONE);
        model.setProgramarPago(programarPagoEntity);
        model.setMotivoAnulacion("Nuevo Motivo");
        model.setFechaCreacion(LocalDate.now().plusDays(1));
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

    @Test
    @DisplayName("Test EgresoCajaEntity AllArgsConstructor")
    void testModelEgresoCajaEntityAllArgsConstructor() {
        ProgramarPagoEntity programarPagoEntity = new ProgramarPagoEntity();
        AnulacionEntity anulacionEntity = new AnulacionEntity(1, TipoMovimiento.EGRESO,null,new EgresoCajaEntity(), null, LocalDate.now(), LocalTime.now(), EstadoAnulacion.PENDIENTE);
        EgresoCajaEntity model = new EgresoCajaEntity(10, BigInteger.ONE, 20, programarPagoEntity, "Nuevo Motivo", LocalDate.now().plusDays(1), LocalTime.now(),anulacionEntity, new TrasladoEntity(), new AperturaCajaEntity());
        Assertions.assertNotNull(model.toString());
    }
}
