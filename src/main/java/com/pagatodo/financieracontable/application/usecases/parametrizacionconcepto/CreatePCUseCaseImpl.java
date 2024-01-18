package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.CreatePCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePCUseCaseImpl implements CreatePCUseCase{
	
	private final ParametrizacionConceptoPort parametrizacionConceptoPort;

	@Transactional
	@Override
	public ParametrizacionConcepto execute(ParametrizacionConcepto parametrizacionConcepto) {

		parametrizacionConcepto.setFechaCreacion(LocalDate.now());
		parametrizacionConcepto.setHoraCreacion(LocalTime.now());
		
		return parametrizacionConceptoPort.save(parametrizacionConcepto);
	}

}
