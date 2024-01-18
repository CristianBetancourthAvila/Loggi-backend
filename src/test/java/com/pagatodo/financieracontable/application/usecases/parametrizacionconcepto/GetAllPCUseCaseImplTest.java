package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

@ExtendWith(MockitoExtension.class)
class GetAllPCUseCaseImplTest {
	
	@InjectMocks
	private GetAllPCUseCaseImpl getAllPCUseCaseImpl;
	
	@Mock
	private ParametrizacionConceptoPort parametrizacionConceptoPort;
	
	@Test
	void GetAllPCUseCase_execute_succsess() throws NotFoundException{
		
		ParametrizacionConcepto out = new ParametrizacionConcepto();
		out.setEstado(Estado.INACTIVO);
		
		PageModel<List<ParametrizacionConcepto>> page = new PageModel<>(List.of(out),BigInteger.valueOf(1L));
		
		when(parametrizacionConceptoPort.findAllByCodigoConceptoAndTipo(any(String.class),any(String.class),any(Integer.class),any(Integer.class))).thenReturn(page);
		
		var result = getAllPCUseCaseImpl.execute("1010","101",1,1);
		
		assertEquals(page, result);

	}
}
