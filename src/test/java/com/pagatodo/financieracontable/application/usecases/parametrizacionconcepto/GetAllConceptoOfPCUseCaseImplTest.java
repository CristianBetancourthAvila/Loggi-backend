package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

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
class GetAllConceptoOfPCUseCaseImplTest {
	
	@InjectMocks
	private GetAllConceptoOfPCUseCaseImpl getAllConceptoOfPCUseCaseImpl;
	
	@Mock
	private ParametrizacionConceptoPort parametrizacionConceptoPort;
	
	@Test
	void GetAllConceptoOfPCUseCase_execute_succsess() throws NotFoundException{
		
		ParametrizacionConcepto out = new ParametrizacionConcepto();
		out.setEstado(Estado.INACTIVO);
		
		
		when(parametrizacionConceptoPort.findByConceptoAndNombre(any(Long.class),any(String.class))).thenReturn(List.of(out));
		
		var result = getAllConceptoOfPCUseCaseImpl.execute(1L,"101");
		
		assertEquals(List.of(out), result);

	}
	
	@Test
	void GetAllConceptoOfPCUseCase_executeWithNullAndNotBlank_succsess() throws NotFoundException{
		
		ParametrizacionConcepto out = new ParametrizacionConcepto();
		out.setEstado(Estado.INACTIVO);
		
		
		when(parametrizacionConceptoPort.findByConceptoAndNombre(any(),any(String.class))).thenReturn(List.of(out));
		
		var result = getAllConceptoOfPCUseCaseImpl.execute(null,"123");
		
		assertEquals(List.of(out), result);

	}
	
	@Test
	void GetAllConceptoOfPCUseCase_executeWithNullAndBlank_succsess() throws NotFoundException{
		
		ParametrizacionConcepto out = new ParametrizacionConcepto();
		out.setEstado(Estado.INACTIVO);
		
		when(parametrizacionConceptoPort.findByTop10OrderByCodigoConcepto()).thenReturn(List.of(out));
		
		var result = getAllConceptoOfPCUseCaseImpl.execute(null,"  ");
		
		assertEquals(List.of(out), result);

	}
}
