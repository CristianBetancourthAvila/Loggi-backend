package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

@ExtendWith(MockitoExtension.class)
class UpdatePCUseCaseImplTest {
	
	@InjectMocks
	private UpdatePCUseCaseImpl updatePCUseCaseImpl;
	
	@Mock
	private ParametrizacionConceptoPort parametrizacionConceptoPort;
	
	@Test
	void UpdatePCUseCase_execute_succsess() throws NotFoundException{
		
		ParametrizacionConcepto in = new ParametrizacionConcepto();
		in.setEstado(Estado.ACTIVO);
		
		when(parametrizacionConceptoPort.update(anyLong(),any(ParametrizacionConcepto.class))).thenReturn(in);
		
		var result = updatePCUseCaseImpl.execute(1l,new ParametrizacionConcepto());
		
		assertEquals(in, result);

	}
}
