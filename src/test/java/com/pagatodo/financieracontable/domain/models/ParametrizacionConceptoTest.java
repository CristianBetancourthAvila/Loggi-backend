package com.pagatodo.financieracontable.domain.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

@ExtendWith(MockitoExtension.class)
class ParametrizacionConceptoTest {
	
    @Test
    void testParametrizacionConcepto() {
    	
    	ParametrizacionConcepto model = new ParametrizacionConcepto();
    	
    	model.setId(1L);
    	model.setCodigoConcepto(1L);
    	model.setProgramable(true);
    	model.setAnulable(false);
    	model.setImprimible(true);
    	model.setDescripcion("Ejemplo de descripción");
    	model.setFechaCreacion(LocalDate.now());
    	model.setEstado(Estado.ACTIVO); 

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
