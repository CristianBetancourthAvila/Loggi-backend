package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class AnulacionResponseTest {

    @Test
    @DisplayName("Test for AnulacionResponse model")
    void testModelAAnulacionResponse() {
        AnulacionResponse model = new AnulacionResponse();
        model.setId(1);
        model.setMotivo("MOTIVO");
        model.setValor(20000L);
        model.setCodigoConcepto(1L);
        model.setNombreConcepto("NOMBRE_CONCEPTO");
        model.setTipoConcepto("TIPO");
        model.setAutorizado(1);
        model.setIdentificacion(4L);
        model.setFechaSolicitud(LocalDate.now());
        model.setHoraSolicitud(LocalTime.now());
        model.setEstadoAnulacion(EstadoAnulacion.PENDIENTE);
        model.setMovimientoId(4);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
