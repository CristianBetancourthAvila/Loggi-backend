package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.UpdateStatusPCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateStatusPCUseCaseImpl implements UpdateStatusPCUseCase{
	
	private final ParametrizacionConceptoPort parametrizacionConceptoPort;
	
	@Transactional
	@Override
	public ParametrizacionConcepto execute(Long id, Estado estado) throws NotFoundException {

		ParametrizacionConcepto parametrizacionConcepto = new ParametrizacionConcepto();
		parametrizacionConcepto.setEstado(estado);
		
		return parametrizacionConceptoPort.update(id, parametrizacionConcepto);
	}

}
