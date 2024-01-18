package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.enums.ExportType;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ParametrizacionConceptoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConceptoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ParametrizacionConceptoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.parametrizacionconcepto.ParametrizacionConceptoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.CreatePCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GenerateReportUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GetAllConceptoOfPCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GetAllPCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.UpdatePCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.UpdateStatusPCUseCase;

@ExtendWith(MockitoExtension.class)
class ParametrizacionConceptoControllerTest {
	
	@InjectMocks
	private ParametrizacionConceptoController controller;
	
	@Mock
    private CreatePCUseCase createPCUseCase;
	
	@Mock
    private GetAllPCUseCase getAllPCUseCase;
	
	@Mock
    private UpdatePCUseCase updatePCUseCase;
	
	@Mock
    private UpdateStatusPCUseCase updateStatusPCUseCase;
	
	@Mock
	private GetAllConceptoOfPCUseCase getAllConceptoOfPCUseCase;
	
	@Mock
	private GenerateReportUseCase generateReportUseCase;

	@Mock
    private ParametrizacionConceptoMapperApi parametrizacionConceptoMapperApi;

    private static ParametrizacionConceptoRequest request;
    private static ParametrizacionConceptoResponse response;
    private static ParametrizacionConcepto domain;
    
    @BeforeAll
    static void configInitial() {
    	
    	request = new ParametrizacionConceptoRequest();
    	
    	response = new ParametrizacionConceptoResponse();
    	
    	domain = new ParametrizacionConcepto();
    	
    }
    @Test
    void findWithFilter_success() {
    	
    	PageModel<List<ParametrizacionConcepto>> respPage = new PageModel<List<ParametrizacionConcepto>>(List.of(domain),BigInteger.valueOf(1));
        
        Mockito.when(parametrizacionConceptoMapperApi.domainsToResponses(any()))
        		.thenReturn(List.of(response));
        
        Mockito.when(getAllPCUseCase.execute(any(String.class),any(String.class),any(Integer.class),any(Integer.class))).thenReturn(respPage);
        
        var result = controller.findWithFilter("132","123",1,1);
        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
    

	@Test
    void create_success() {

        Mockito.when(parametrizacionConceptoMapperApi.requestToDomain(any(ParametrizacionConceptoRequest.class)))
                .thenReturn(domain);
        
        Mockito.when(parametrizacionConceptoMapperApi.domainToResponse(any(ParametrizacionConcepto.class)))
        		.thenReturn(response);
        
        Mockito.when(createPCUseCase.execute(any(ParametrizacionConcepto.class))).thenReturn(domain);
        
        ResponseEntity<ParametrizacionConceptoResponse> result = controller.create(request);
        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }
    
    @Test
    void update_success() throws Exception {

        Mockito.when(parametrizacionConceptoMapperApi.requestToDomain(any(ParametrizacionConceptoRequest.class)))
                .thenReturn(domain);
        
        Mockito.when(parametrizacionConceptoMapperApi.domainToResponse(any(ParametrizacionConcepto.class)))
        		.thenReturn(response);
        
        Mockito.when(updatePCUseCase.execute(anyLong(),any(ParametrizacionConcepto.class))).thenReturn(domain);
        
        ResponseEntity<ParametrizacionConceptoResponse> result = controller.update(1L,request);
        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
    
    @Test
    void updateStatus_success() throws Exception {

        
        Mockito.when(parametrizacionConceptoMapperApi.domainToResponse(any(ParametrizacionConcepto.class)))
        		.thenReturn(response);
        
        Mockito.when(updateStatusPCUseCase.execute(anyLong(),any(Estado.class))).thenReturn(domain);
        
        ResponseEntity<ParametrizacionConceptoResponse> result = controller.updateStatus(1L,Estado.ACTIVO);
        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
    
    @Test
    void findConceptoWithFilter_success() throws Exception {
        
        Mockito.when(parametrizacionConceptoMapperApi.domainsToConceptoResponses(anyList()))
        		.thenReturn(List.of( new ConceptoResponse()));
        
        Mockito.when(getAllConceptoOfPCUseCase.execute(anyLong(),any(String.class))).thenReturn(List.of(domain));
        
        ResponseEntity<List<ConceptoResponse>> result = controller.findConceptoWithFilter(1L,"123");
        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
    
    @Test
    void report_success() throws Exception {
    	FileReportResponse response = new FileReportResponse();
    	response.setFile("base64");
        
        Mockito.when(parametrizacionConceptoMapperApi.domainToResponse(any(FileReport.class)))
        		.thenReturn(response);
        
        Mockito.when(generateReportUseCase.execute(any(ExportType.class),any(String.class),any(String.class))).thenReturn(new FileReport());
        
        ResponseEntity<FileReportResponse> result = controller.report(ExportType.PDF,"123","123");
        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
}
