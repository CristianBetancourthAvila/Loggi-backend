package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.CarteraApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CarteraRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CarteraUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CarteraResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.cartera.CarteraMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.cartera.CarteraUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.cartera}")
public class CarteraController implements CarteraApi {

    private final CarteraMapperApi carteraMapperApi;

    private final CarteraUseCase carteraUseCase;

    @PostMapping
    @Override
    public ResponseEntity<CarteraResponse> save(@RequestBody CarteraRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        var result = carteraUseCase.create(carteraMapperApi.requestToDomain(request));
        return new ResponseEntity<>(carteraMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @GetMapping("/seller/{sellerId}")
    @Override
    public ResponseEntity<CarteraResponse> findCarteraByVendedorId(@PathVariable Integer sellerId) throws NotFoundException {
        var result = carteraUseCase.findCarteraByVendedorId(sellerId);
        return new ResponseEntity<>(carteraMapperApi.domainToResponse(result), HttpStatus.OK);
    }

    @PutMapping
    @Override
    public ResponseEntity<CarteraResponse> update(@RequestBody CarteraUpdateRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        var result = carteraUseCase.update(carteraMapperApi.updateRequestToDomain(request));
        return new ResponseEntity<>(carteraMapperApi.domainToResponse(result), HttpStatus.OK);
    }
}
