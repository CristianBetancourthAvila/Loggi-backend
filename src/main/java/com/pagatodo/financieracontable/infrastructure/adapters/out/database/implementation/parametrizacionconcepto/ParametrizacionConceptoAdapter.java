package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.parametrizacionconcepto;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.parametrizacionconcepto.ParametrizacionConceptoNotFoundException;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ParametrizacionConceptoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.parametrizacionconcepto.ParametrizacionConceptoMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.ConceptoProjection;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.ParametrizacionConceptoRepository;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.specifications.ParametrizacionConceptoSpeficication;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ParametrizacionConceptoAdapter implements ParametrizacionConceptoPort {
	
    private final ParametrizacionConceptoRepository parametrizacionConceptoRepository;
    private final ParametrizacionConceptoMapper parametrizacionConceptoMapper;
    
	@Override
	public PageModel<List<ParametrizacionConcepto>> findAllByCodigoConceptoAndTipo(String codigoConcepto, String tipoConcepto,Integer rowsPerPage, Integer skip) {
		
		Integer pageNumber = (int) Math.ceil((double)skip/rowsPerPage);
		Pageable pageable = PageRequest.of(pageNumber, rowsPerPage);
		
		Specification<ParametrizacionConceptoEntity> specifications = ParametrizacionConceptoSpeficication.applyFilters(codigoConcepto,tipoConcepto);

		Page<ParametrizacionConceptoEntity> page = parametrizacionConceptoRepository.findAll(specifications,pageable);

        return new PageModel<>(parametrizacionConceptoMapper.entitiesToDomains(page.getContent()), BigInteger.valueOf(page.getTotalElements()));

	}

	@Override
	public ParametrizacionConcepto save(ParametrizacionConcepto parametrizacionConcepto) {
		
		ParametrizacionConceptoEntity parametrizacionConceptoEntity =  parametrizacionConceptoMapper.domainToEntity(parametrizacionConcepto);
        return parametrizacionConceptoMapper.entityToDomain(parametrizacionConceptoRepository.save(parametrizacionConceptoEntity));
	}

	@Override
	public ParametrizacionConcepto update(Long id, ParametrizacionConcepto parametrizacionConcepto) throws NotFoundException {
		
		ParametrizacionConceptoNotFoundException errorNotFound =  new ParametrizacionConceptoNotFoundException();
		errorNotFound.addParams(id);
		
		ParametrizacionConceptoEntity target = parametrizacionConceptoRepository.findById(id).orElseThrow(() -> errorNotFound);
		parametrizacionConcepto.setId(id);

		parametrizacionConceptoMapper.mergeToEntity(target, parametrizacionConcepto);
		
		return parametrizacionConceptoMapper.entityToDomain(parametrizacionConceptoRepository.save(target));

	}

	@Override
	public ParametrizacionConcepto findById(Long id) {
		return parametrizacionConceptoMapper.entityToDomain(parametrizacionConceptoRepository.findById(id).orElse(null));
	}

	@Override
	public List<ParametrizacionConcepto> findByConceptoAndNombre(Long concepto, String nombre) {
		
		List<ConceptoProjection>  result = parametrizacionConceptoRepository.findByCodigoConceptoAndNombreConcepto(concepto, nombre);

		return parametrizacionConceptoMapper.conceptosToDomains(result);
	}
	
	@Override
	public List<ParametrizacionConcepto> findByTop10OrderByCodigoConcepto() {
		
		List<ConceptoProjection>  result = parametrizacionConceptoRepository.findByTop10OrderByCodigoConcepto();

		return parametrizacionConceptoMapper.conceptosToDomains(result);
	}

	@Override
	public List<ParametrizacionConcepto> findAllByCodigoConceptoAndTipo(String codigoNombreConcepto, String tipo) {
		
		Specification<ParametrizacionConceptoEntity> specifications = ParametrizacionConceptoSpeficication.applyFilters(codigoNombreConcepto,tipo);

		List<ParametrizacionConceptoEntity> result = parametrizacionConceptoRepository.findAll(specifications);

		return parametrizacionConceptoMapper.entitiesToDomains(result);
	}

}
