package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.UpdatePCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdatePCUseCaseImpl implements UpdatePCUseCase{
	
	private final ParametrizacionConceptoPort parametrizacionConceptoPort;
	
	@Transactional
	@Override
	public ParametrizacionConcepto execute(Long id, ParametrizacionConcepto parametrizacionConcepto) throws NotFoundException {
		
		return parametrizacionConceptoPort.update(id, parametrizacionConcepto);
	}

}
