package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto.helpers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

@ExtendWith(MockitoExtension.class)
class ParametrizacionConceptoTableTest {
	
    @Test
    void testParametrizacionConceptoTable() {
    	
    	ParametrizacionConceptoTable model = new ParametrizacionConceptoTable();
    	
    	model.setId(1L);
    	model.setCodigoConcepto(1L);
    	model.setProgramable(true);
    	model.setAnulable(false);
    	model.setImprimible(true);
    	model.setDescripcion("Ejemplo de descripción");
    	model.setFechaCreacion(LocalDate.now());
    	model.setEstado(Estado.ACTIVO); 
    	
    	model.setSubcategoria("sub");
    	model.setTipologia("tip");
    	model.setCodigoCuenta("123");
    	model.setCodigoCuentaAnulable("123");
    	model.setFechaGestion("11/11/2023");
    	model.setEstadoRegistro("Activo");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
