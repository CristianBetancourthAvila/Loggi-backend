package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.AperturaCajaApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.AperturaCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.AperturaCajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.aperturacaja.AperturaCajaMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.aperturacaja.AperturaCajaUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.apertura-caja}")
public class AperturaCajaController implements AperturaCajaApi {

    private final AperturaCajaUseCase aperturaCajaUseCase;

    private final AperturaCajaMapperApi aperturaCajaMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<Void> save(@RequestBody @Valid AperturaCajaRequest request) throws BusinessException, NotFoundException {
        this.aperturaCajaUseCase.create(aperturaCajaMapperApi.requestToDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("validate/{cajaId}")
    public ResponseEntity<AperturaCajaResponse> validate(@PathVariable BigInteger cajaId, @RequestParam("showData") boolean showData) throws BusinessException, NotFoundException {
        boolean confirmation = this.aperturaCajaUseCase.validateStatus(cajaId);
        Caja caja = this.aperturaCajaUseCase.findAssignedCajaByCajaId(cajaId);
        AperturaCaja lastRecord = this.aperturaCajaUseCase.findLastRecord(caja.getId());
        //TODO: Devolver el saldo inicial basado en el saldo final de cierre de caja.
        return new ResponseEntity<>(aperturaCajaMapperApi.domainToResponse(caja, confirmation, lastRecord, showData, 20000L), HttpStatus.OK);
    }

    @Override
    @PatchMapping("{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        this.aperturaCajaUseCase.updateStatus(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
