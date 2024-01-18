package com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto;

import java.util.List;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;

public interface GetAllConceptoOfPCUseCase {
	
	List<ParametrizacionConcepto> execute(Long concepto, String nombre);

}
