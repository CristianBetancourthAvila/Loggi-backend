package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GetAllConceptoOfPCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAllConceptoOfPCUseCaseImpl implements GetAllConceptoOfPCUseCase{
	
	private final ParametrizacionConceptoPort parametrizacionConceptoPort;

	@Override
	public List<ParametrizacionConcepto> execute(Long concepto, String nombre) {
		
		List<ParametrizacionConcepto> result = List.of();
		
		if(concepto!=null || !StringUtils.isBlank(nombre)) {
			result = parametrizacionConceptoPort.findByConceptoAndNombre(concepto, nombre);
		}else {
			result = parametrizacionConceptoPort.findByTop10OrderByCodigoConcepto();
		}
		
		return result;
	}

}
