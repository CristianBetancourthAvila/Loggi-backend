package com.pagatodo.financieracontable.infrastructure.adapters.out.database.specifications;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ParametrizacionConceptoEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

@ExtendWith(MockitoExtension.class)
class ParametrizacionConceptoSpeficicationTest {
	
    @Mock
    private Root<ParametrizacionConceptoEntity> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;
     
    @Mock
    private Root<ParametrizacionConceptoEntity> rootFraccion;
    
    @Test
    void toPredicate_withParameterNotNull() {

    	Specification<ParametrizacionConceptoEntity> specification = ParametrizacionConceptoSpeficication.applyFilters("132","123");

        Path<Object> mockPath = Mockito.mock();
       
        when(root.get(any(String.class))).thenReturn(mockPath); 
        
        specification.toPredicate(root, query, criteriaBuilder);
        
		assertAll("resultado",
				() -> assertNotNull(criteriaBuilder));
    }
    
    @Test
    void toPredicate_withParameterisEmptyTrue() {

    	Specification<ParametrizacionConceptoEntity> specification = ParametrizacionConceptoSpeficication.applyFilters("","");

        Path<Object> mockPath = Mockito.mock();
        
        when(root.get(any(String.class))).thenReturn(mockPath); 

        specification.toPredicate(root, query, criteriaBuilder);
        
		assertAll("resultado",
				() -> assertNotNull(criteriaBuilder));
    }
    
    @Test
    void toPredicate_withParameterNull() {

    	Specification<ParametrizacionConceptoEntity> specification = ParametrizacionConceptoSpeficication.applyFilters(null,null);

        specification.toPredicate(root, query, criteriaBuilder);
        
		assertAll("resultado",
				() -> assertNotNull(criteriaBuilder));
    }
}
