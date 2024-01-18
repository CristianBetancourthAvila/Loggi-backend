package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.parametrizacionconcepto;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.parametrizacionconcepto.ParametrizacionConceptoErrorCodes;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ParametrizacionConceptoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.parametrizacionconcepto.ParametrizacionConceptoMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.ConceptoProjection;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.ParametrizacionConceptoRepository;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.specifications.ParametrizacionConceptoSpeficication;

@ExtendWith(MockitoExtension.class)
class ParametrizacionConceptoAdapterTest {
	
	@InjectMocks
	private ParametrizacionConceptoAdapter adapter;
	
	@Mock
    private ParametrizacionConceptoRepository repository;
	
	@Mock
    private ParametrizacionConceptoMapper mapper;
	
	private static ParametrizacionConceptoEntity entity;
	
	private static ParametrizacionConcepto domain;
	
    @BeforeAll
    static void configInitial() {
    	entity = new ParametrizacionConceptoEntity();
    	domain = new ParametrizacionConcepto();
    }

    @Test
    void findAllByCodigoConceptoAndTipo_success() {
    	
		Page<ParametrizacionConceptoEntity> page = new PageImpl<>(Collections.singletonList(entity), PageRequest.of(0, 1),1);

        Mockito.when(repository.findAll(any(ParametrizacionConceptoSpeficication.class),any(Pageable.class))).thenReturn(page);
        Mockito.when(mapper.entitiesToDomains(anyList())).thenReturn(List.of(domain));

        var result = adapter.findAllByCodigoConceptoAndTipo("","",2,2);
        
		assertAll("resultado",
				() -> assertNotNull(result),
				() -> assertEquals(1l, result.total().longValue()));
    }
    
    @Test
    void save_success() {

        Mockito.when(mapper.domainToEntity(any(ParametrizacionConcepto.class))).thenReturn(entity);
        Mockito.when(mapper.entityToDomain(any(ParametrizacionConceptoEntity.class))).thenReturn(domain);
        
        Mockito.when(repository.save(any(ParametrizacionConceptoEntity.class))).thenReturn(entity);
        
        var result = adapter.save(domain);
        
        assertNotNull(result);
        assertEquals(result, domain);
    }
    
    @Test
    void update_success() throws Exception {
    	
        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(entity));

        Mockito.when(mapper.entityToDomain(any(ParametrizacionConceptoEntity.class))).thenReturn(domain);

        Mockito.when(repository.save(any(ParametrizacionConceptoEntity.class))).thenReturn(entity);
        
        var result = adapter.update(1l,domain);
        
        assertNotNull(result);
        assertEquals(result, domain);
    }
    
    @Test
    void update_NotFound_Error() throws Exception {
    	
        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.empty());
        
		assertThatExceptionOfType(NotFoundException.class)
		.isThrownBy(() -> adapter.update(1l,domain))
		.withMessage(ParametrizacionConceptoErrorCodes.NOT_FOUND.getLocalizedMessage());
		
    }
    
    @Test
    void findById_success() throws Exception {
    	
        Mockito.when(repository.findById(any(Long.class))).thenReturn(Optional.of(entity));

        Mockito.when(mapper.entityToDomain(any(ParametrizacionConceptoEntity.class))).thenReturn(domain);

        var result = adapter.findById(1l);
        
        assertNotNull(result);
        assertEquals(result, domain);
    }
    
    @Test
    void findByConceptoAndNombre_success() throws Exception {
    	ConceptoProjection projection = Mockito.mock(ConceptoProjection.class);
    	
        Mockito.when(repository.findByCodigoConceptoAndNombreConcepto(any(Long.class),any(String.class))).thenReturn(List.of(projection));

        Mockito.when(mapper.conceptosToDomains(any())).thenReturn(List.of(domain));

        var result = adapter.findByConceptoAndNombre(1l,"123");
        
        assertNotNull(result);
        assertEquals(result, List.of(domain));
    }
    
    @Test
    void findByTop10OrderByCodigoConcepto_success() throws Exception {
    	ConceptoProjection projection = Mockito.mock(ConceptoProjection.class);
    	
        Mockito.when(repository.findByTop10OrderByCodigoConcepto()).thenReturn(List.of(projection));

        Mockito.when(mapper.conceptosToDomains(any())).thenReturn(List.of(domain));

        var result = adapter.findByTop10OrderByCodigoConcepto();
        
        assertNotNull(result);
        assertEquals(result, List.of(domain));
    }
    
    @Test
    void findAllByCodigoConceptoAndTipo_withoutPage_success() {
    
        Mockito.when(repository.findAll(any(ParametrizacionConceptoSpeficication.class))).thenReturn(List.of(entity));
        Mockito.when(mapper.entitiesToDomains(anyList())).thenReturn(List.of(domain));

        var result = adapter.findAllByCodigoConceptoAndTipo("","");
        
		assertAll("resultado",
				() -> assertNotNull(result));
    }

}
