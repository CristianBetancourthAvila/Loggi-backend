package com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;

public interface CreatePCUseCase {
	
	ParametrizacionConcepto execute(ParametrizacionConcepto parametrizacionConcepto);

}
