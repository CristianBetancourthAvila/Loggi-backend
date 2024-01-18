package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.junit.PropertyTester;

@ExtendWith(MockitoExtension.class)
class ConceptoResponseTest {
	
    @Test
    void testConceptoResponse() {
    	
    	ConceptoResponse model = new ConceptoResponse();
    	
    	model.setCodigo(1l);
    	model.setNombre("NAME");
    	model.setParametrizacionConceptoId(1L);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
