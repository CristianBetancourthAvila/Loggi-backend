package com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;

public interface UpdatePCUseCase {
	
	ParametrizacionConcepto execute(Long id, ParametrizacionConcepto parametrizacionConcepto) throws NotFoundException;

}
