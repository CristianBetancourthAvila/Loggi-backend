package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

@ExtendWith(MockitoExtension.class)
class ParametrizacionConceptoRequestTest {
	
    @Test
    void testParametrizacionConcepto() {
    	
    	ParametrizacionConceptoRequest model = new ParametrizacionConceptoRequest();
    	
    	model.setId(1L);
    	model.setCodigoConcepto(1L);
    	model.setNombreConcepto("CD01");
    	model.setTipoConcepto("tipo");
    	model.setProgramable(true);
    	model.setAnulable(false);
    	model.setImprimible(true);
    	model.setDescripcion("Ejemplo de descripción");
    	model.setEstado(Estado.ACTIVO); 

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
