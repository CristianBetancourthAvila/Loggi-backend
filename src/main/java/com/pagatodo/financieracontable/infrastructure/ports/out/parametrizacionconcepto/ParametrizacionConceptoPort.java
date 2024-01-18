package com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto;

import java.util.List;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.records.PageModel;

public interface ParametrizacionConceptoPort {
	
	PageModel<List<ParametrizacionConcepto>> findAllByCodigoConceptoAndTipo(String codigoNombreConcepto, String tipo,Integer rowsPerPage, Integer skip);
	
	ParametrizacionConcepto save(ParametrizacionConcepto parametrizacionConcepto);
	
	ParametrizacionConcepto update(Long id, ParametrizacionConcepto parametrizacionConcepto) throws NotFoundException;
	
	ParametrizacionConcepto findById(Long id);
	
	List<ParametrizacionConcepto> findByConceptoAndNombre(Long concepto, String nombre);
	
	List<ParametrizacionConcepto> findByTop10OrderByCodigoConcepto();
	
	List<ParametrizacionConcepto> findAllByCodigoConceptoAndTipo(String codigoNombreConcepto, String tipo);

}
