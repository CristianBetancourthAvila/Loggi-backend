package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

@ExtendWith(MockitoExtension.class)
class ParametrizacionConceptoResponseTest {

    @Test
    void testParametrizacionConceptoResponse() {
    	
    	ParametrizacionConceptoResponse model = new ParametrizacionConceptoResponse();
    	
    	model.setId(1L);
    	model.setCodigoConcepto(1L);
    	model.setNombreConcepto("CD01");
    	model.setTipoConcepto("tipo");
    	model.setProgramable(true);
    	model.setAnulable(false);
    	model.setImprimible(true);
    	model.setDescripcion("Ejemplo de descripción");
    	model.setFechaCreacion(LocalDate.now());
    	model.setHoraCreacion(LocalTime.now());
    	model.setEstado(Estado.ACTIVO); 

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
