package com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ConceptoProjectionTest {

    private static ConceptoProjection model;

    @Test
    @DisplayName("Test for EgresoCajaResponse model")
    void testConceptoProjection() {
        model = new ConceptoProjection(1L,"132",1L);

        assertNotNull(model.codigoConcepto());
        assertNotNull(model.nombreConcepto());

    }
}