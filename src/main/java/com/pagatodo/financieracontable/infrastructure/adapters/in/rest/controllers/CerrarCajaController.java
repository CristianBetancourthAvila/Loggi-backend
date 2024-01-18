package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.CerrarCajaApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CerrarCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.cerrarcaja.CerrarCajaMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.cerrarcaja.CerrarCajaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.cerrar-caja}")
public class CerrarCajaController implements CerrarCajaApi {

    private final CerrarCajaUseCase cerrarCajaUseCase;

    private final CerrarCajaMapperApi cerrarCajaMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<Void> save(@RequestBody CerrarCajaRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        cerrarCajaUseCase.create(cerrarCajaMapperApi.requestToDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
