package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.enums.ExportType;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.ParametrizacionConceptoApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ParametrizacionConceptoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConceptoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ParametrizacionConceptoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.parametrizacionconcepto.ParametrizacionConceptoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.CreatePCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GenerateReportUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GetAllConceptoOfPCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GetAllPCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.UpdatePCUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.UpdateStatusPCUseCase;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;

@AllArgsConstructor
@RestController
@RequestMapping(ParametrizacionConceptoController.PARAMETRIZACION_CONCEPTO)
public class ParametrizacionConceptoController implements ParametrizacionConceptoApi{
	
	public static final String PARAMETRIZACION_CONCEPTO = "/${request-mapping.controller.parametrizacion-concepto}";

    private final CreatePCUseCase createPCUseCase;
    private final GetAllPCUseCase getAllPCUseCase;
    private final UpdatePCUseCase updatePCUseCase;
    private final UpdateStatusPCUseCase updateStatusPCUseCase;
    private final GetAllConceptoOfPCUseCase getAllConceptoOfPCUseCase;
    private final GenerateReportUseCase generateReportUseCase;

    private final ParametrizacionConceptoMapperApi parametrizacionConceptoMapperApi;
    
	@GetMapping
	@Override
	public ResponseEntity<PageResponse<List<ParametrizacionConceptoResponse>>> findWithFilter(String codigoNombreConcepto,
			String tipo, Integer rowsPerPage, Integer skip) {
		
        var paginatedResult = getAllPCUseCase.execute(codigoNombreConcepto, tipo,rowsPerPage, skip);
        
        PageResponse<List<ParametrizacionConceptoResponse>> resultPage = new PageResponse<>();
        resultPage.setData(parametrizacionConceptoMapperApi.domainsToResponses(paginatedResult.data()));
        resultPage.setTotal(paginatedResult.total());
        
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}
	
	@PostMapping
	@Override
	public ResponseEntity<ParametrizacionConceptoResponse> create(@RequestBody ParametrizacionConceptoRequest request) {
		
        var result = createPCUseCase.execute(parametrizacionConceptoMapperApi.requestToDomain(request));
        return new ResponseEntity<>(parametrizacionConceptoMapperApi.domainToResponse(result) , HttpStatus.CREATED);
	}
	
	@Override
	@PutMapping(path = "/{id}")
	public ResponseEntity<ParametrizacionConceptoResponse> update(@PathVariable("id") final Long id, @RequestBody ParametrizacionConceptoRequest request)
			throws NotFoundException {
		
		var result = updatePCUseCase.execute(id, parametrizacionConceptoMapperApi.requestToDomain(request));
		
        return new ResponseEntity<>(parametrizacionConceptoMapperApi.domainToResponse(result), HttpStatus.OK);

	}

	@Override
	@PatchMapping(path = "/{id}")
	public ResponseEntity<ParametrizacionConceptoResponse> updateStatus(@PathVariable("id") final Long id, @RequestParam(required = true) Estado estado) throws NotFoundException {
		
		var result = updateStatusPCUseCase.execute(id, estado);
		
        return new ResponseEntity<>(parametrizacionConceptoMapperApi.domainToResponse(result), HttpStatus.OK);
	}
	
	@GetMapping(path = "/conceptos")
	@Override
	public ResponseEntity<List<ConceptoResponse>> findConceptoWithFilter(Long codigo, String nombre) {

		var result = getAllConceptoOfPCUseCase.execute(codigo, nombre);
		
		return new ResponseEntity<>(parametrizacionConceptoMapperApi.domainsToConceptoResponses(result), HttpStatus.OK);
	}

	@GetMapping(path = "/downloads")
	@Override
	public ResponseEntity<FileReportResponse> report(ExportType type, String codigoNombreConcepto, String tipo) throws JRException {
		
		var result  = generateReportUseCase.execute(type, codigoNombreConcepto, tipo);
		
		return new ResponseEntity<>(parametrizacionConceptoMapperApi.domainToResponse(result), HttpStatus.OK);
	}


}
