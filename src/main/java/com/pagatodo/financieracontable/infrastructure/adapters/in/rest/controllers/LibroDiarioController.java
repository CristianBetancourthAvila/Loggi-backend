package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioBusinessException;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.LibroDiarioApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.LibroDiarioRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.LibroDiarioResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.librodiario.LibroDiarioMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.librodiario.GetLibroDiarioByCriteriaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(LibroDiarioController.LIBRODIARIO)
public class LibroDiarioController implements LibroDiarioApi {

    public static final String LIBRODIARIO = "/${request-mapping.controller.libro-diario}";
    private final LibroDiarioMapperApi libroDiarioMapperApi;
    private final GetLibroDiarioByCriteriaUseCase getLibroDiarioByCriteriaUseCase;

    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<LibroDiarioResponse>>> findWithFilter(LibroDiarioRqFilter libroDiarioRqFilter, Integer rowsPerPage, Integer skip) throws LibroDiarioBusinessException {
        LibroDiarioFilter libroDiarioFilter = libroDiarioMapperApi.requestToDomain(libroDiarioRqFilter);
        var paginatedResult = getLibroDiarioByCriteriaUseCase.findWithFiler(libroDiarioFilter,rowsPerPage, skip);
        PageResponse<List<LibroDiarioResponse>> resultPage = new PageResponse<>();
        List<LibroDiarioResponse> libroDiarioResponses = libroDiarioMapperApi.domainsToResponses(paginatedResult.data());
        resultPage.setData(libroDiarioResponses);
        resultPage.setTotal(paginatedResult.total());
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }
}
