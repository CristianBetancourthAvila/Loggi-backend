package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.TrasladoApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.TrasladoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoFilterByDateResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoSendReceiveResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.traslado.TrasladoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.traslado.TrasladoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.traslado}")
public class TrasladoController implements TrasladoApi {

    private final TrasladoUseCase trasladoUseCase;

    private final TrasladoMapperApi trasladoMapperApi;
    @PostMapping
    @Override
    public ResponseEntity<TrasladoResponse> save(@RequestBody TrasladoRequest request) throws BusinessException {
        var result = trasladoUseCase.create(trasladoMapperApi.requestToDomain(request));
        return new ResponseEntity<>(trasladoMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<TrasladoFilterByDateResponse>>> findTrasladosByDate(LocalDate date, Integer rowsPerPage, Integer skip) {
       var paginatedResult = trasladoUseCase.findTrasladosByDate(date,rowsPerPage, skip);
       PageResponse<List<TrasladoFilterByDateResponse>> resultPage = new PageResponse<>();
       resultPage.setData(trasladoMapperApi.domainsToFilterResponses(paginatedResult.data()));
       resultPage.setTotal(paginatedResult.total());
       return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

    @GetMapping("/caja")
    @Override
    public ResponseEntity<PageResponse<List<TrasladoSendReceiveResponse>>> findSendReceiveTrasladosByCaja(BigInteger cajaId, Boolean send, Integer rowsPerPage, Integer skip) {
        var paginatedResult = trasladoUseCase.findSendReceiveTrasladosByCaja(cajaId,send, rowsPerPage, skip);
        PageResponse<List<TrasladoSendReceiveResponse>> resultPage = new PageResponse<>();
        resultPage.setData(trasladoMapperApi.domainsToSendReceiveResponses(paginatedResult.data(), send));
        resultPage.setTotal(paginatedResult.total());
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }
}
