package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.ConciliacionApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.ConciliacionRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ConciliacionRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConciliacionFilterResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConciliacionResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.conciliacion.ConciliacionMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.conciliacion.CreateConciliacionUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.conciliacion.GetConciliacionByCriteriaUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ConciliacionController.CONCILIACION)
public class ConciliacionController implements ConciliacionApi {

    public static final String CONCILIACION = "/${request-mapping.controller.conciliacion}";
    private final CreateConciliacionUseCase createConciliacionUseCase;
    private final GetConciliacionByCriteriaUseCase getConciliacionByCriteriaUseCase;
    private final ConciliacionMapperApi conciliacionMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<ConciliacionResponse> create(@Valid @RequestBody ConciliacionRequest conciliacionRequest) throws ConciliacionBusinessException {
        var result = createConciliacionUseCase.create(conciliacionMapperApi.requestToDomain(conciliacionRequest));
        return new ResponseEntity<>(conciliacionMapperApi.domainToResponse(result) , HttpStatus.CREATED);
    }
    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<ConciliacionFilterResponse>>> findWithFilter(ConciliacionRqFilter conciliacionRqFilter, Integer rowsPerPage, Integer skip) throws ConciliacionBusinessException {
        var paginatedResult = getConciliacionByCriteriaUseCase.findWithFiler(conciliacionMapperApi.requestFilterToDomain(conciliacionRqFilter), rowsPerPage, skip);
        PageResponse<List<ConciliacionFilterResponse>> resultPage = new PageResponse<>();
        List<ConciliacionFilterResponse> conciliacionResponses = conciliacionMapperApi.domainsToFilterResponse(paginatedResult.data());
        resultPage.setData(conciliacionResponses);
        resultPage.setTotal(paginatedResult.total());
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }
}
