package com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto;

import java.util.List;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.records.PageModel;

public interface GetAllPCUseCase {
	
	PageModel<List<ParametrizacionConcepto>> execute(String codigoNombreConcepto, String tipoConcepto, Integer rowsPerPage, Integer skip);

}
