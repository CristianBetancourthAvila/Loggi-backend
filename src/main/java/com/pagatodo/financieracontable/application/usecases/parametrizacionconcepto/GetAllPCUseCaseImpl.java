package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GetAllPCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAllPCUseCaseImpl implements GetAllPCUseCase{
	
	private final ParametrizacionConceptoPort parametrizacionConceptoPort;
	
	@Override
	public PageModel<List<ParametrizacionConcepto>> execute(String codigoNombreConcepto, String tipoConcepto, Integer rowsPerPage,
			Integer skip) {

		return parametrizacionConceptoPort.findAllByCodigoConceptoAndTipo(codigoNombreConcepto, tipoConcepto, rowsPerPage, skip);
	}

}
