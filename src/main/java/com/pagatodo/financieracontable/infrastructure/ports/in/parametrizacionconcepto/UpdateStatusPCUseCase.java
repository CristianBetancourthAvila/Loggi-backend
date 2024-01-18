package com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

public interface UpdateStatusPCUseCase {
	
	ParametrizacionConcepto execute(Long id, Estado estado) throws NotFoundException;

}
