package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

@ExtendWith(MockitoExtension.class)
class CreatePCUseCaseImplTest {
	
	@InjectMocks
	private CreatePCUseCaseImpl createPCUseCaseImpl;
	
	@Mock
	private ParametrizacionConceptoPort parametrizacionConceptoPort;
	
	@Test
	void CreatePCUseCase_execute_succsess(){
		
		ParametrizacionConcepto in = new ParametrizacionConcepto();
		in.setEstado(Estado.ACTIVO);
		
		when(parametrizacionConceptoPort.save(any(ParametrizacionConcepto.class))).thenReturn(in);
		
		var result = createPCUseCaseImpl.execute(new ParametrizacionConcepto());
		
		assertEquals(in, result);
	}
}
