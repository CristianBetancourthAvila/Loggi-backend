package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto.helpers.ParametrizacionConceptoTable;
import com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto.helpers.ParametrizacionTableMappers;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.enums.ExportType;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

import net.sf.jasperreports.engine.JRException;

@ExtendWith(MockitoExtension.class)
class GenerateReportUseCaseTest {
	
	@InjectMocks
	private GenerateReportUseCaseImpl generateReportUseCaseImpl;
	
	@Mock
	private ParametrizacionConceptoPort parametrizacionConceptoPort;
	
	@Mock
	private ParametrizacionTableMappers mapper;
	
	@Test
	void GenerateReportUseCase_pdf_succsess() throws NotFoundException, JRException{
		
		ParametrizacionConcepto in = new ParametrizacionConcepto();
		in.setEstado(Estado.INACTIVO);
		
		ParametrizacionConceptoTable table = new ParametrizacionConceptoTable();
		
		when(parametrizacionConceptoPort.findAllByCodigoConceptoAndTipo(any(String.class),any(String.class))).thenReturn(List.of(in));
		when(mapper.buildParametrizacionTable(any(ParametrizacionConcepto.class))).thenReturn(table);

		var result = generateReportUseCaseImpl.execute(ExportType.PDF,"","");
		
		assertNotNull(result);

	}
	
	@Test
	void GenerateReportUseCase_excel_succsess() throws NotFoundException, JRException{
		
		ParametrizacionConcepto in = new ParametrizacionConcepto();
		in.setEstado(Estado.INACTIVO);
		
		ParametrizacionConceptoTable table = new ParametrizacionConceptoTable();
		
		when(parametrizacionConceptoPort.findAllByCodigoConceptoAndTipo(any(String.class),any(String.class))).thenReturn(List.of(in));
		when(mapper.buildParametrizacionTable(any(ParametrizacionConcepto.class))).thenReturn(table);

		var result = generateReportUseCaseImpl.execute(ExportType.EXCEL,"","");
		
		assertNotNull(result);

	}
	
	@Test
	void GenerateReportUseCase_csv_succsess() throws NotFoundException, JRException{
		
		ParametrizacionConcepto in = new ParametrizacionConcepto();
		in.setEstado(Estado.INACTIVO);
		
		ParametrizacionConceptoTable table = new ParametrizacionConceptoTable();
		
		when(parametrizacionConceptoPort.findAllByCodigoConceptoAndTipo(any(String.class),any(String.class))).thenReturn(List.of(in));
		when(mapper.buildParametrizacionTable(any(ParametrizacionConcepto.class))).thenReturn(table);

		var result = generateReportUseCaseImpl.execute(ExportType.CSV,"","");
		
		assertNotNull(result);

	}
	
	@Test
	void GenerateReportUseCase_withNullList_succsess() throws NotFoundException, JRException{
		
		ParametrizacionConcepto in = new ParametrizacionConcepto();
		in.setEstado(Estado.INACTIVO);
				
		when(parametrizacionConceptoPort.findAllByCodigoConceptoAndTipo(any(String.class),any(String.class))).thenReturn(List.of());

		var result = generateReportUseCaseImpl.execute(ExportType.PDF,"","");
		
		assertNotNull(result);
		assertNull(result.getFile());

	}
	


}
