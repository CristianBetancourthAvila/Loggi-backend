package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TrasladoTest {

    @Test
    @DisplayName("Test Traslado model")
    void testModelTraslado(){
        Traslado model = new Traslado();
        model.setId(1);
        model.setEstado(EstadoTraslado.PROGRAMADO);
        model.setIngreso(new Ingreso());
        model.setEgresoCaja(new EgresoCaja());
        model.setFechaCreacion(LocalDate.now());
        model.setHoraCreacion(LocalTime.now());
        model.setObservacionTraslado("Observaciones");
        model.setObservacionDiferencia("Observaciones");
        model.setObservacionEnvio("Observaciones");
        model.setObservacionRecepcion("Observaciones");
        model.setSeriePremio(125);
        model.setValorDiferencia(50220);
        model.setCajaDestino(new Caja());
        model.setCajaOrigen(new Caja());
        model.setNumeroBolsa("G2852650");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
