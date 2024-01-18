package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.AperturaCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.AperturaCajaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;

public interface AperturaCajaApi {

    @Operation(summary = "Crear apertura caja", description = "Crea un registro de apertura caja", tags = {"Configuracion Apertura caja"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Se creo con exito la apertura de caja")})
    ResponseEntity<Void> save(AperturaCajaRequest request) throws NotFoundException,BadRequestException, UnknownException, ConnectionException;

    @Operation(summary = "Validar si una caja ya se encuentra abierta", description = "Valida si una caja esta abierta", tags = {"Configuracion Apertura caja"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Valida si una caja esta abierta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AperturaCajaResponse.class)))})
    ResponseEntity<AperturaCajaResponse> validate(BigInteger cajaId, boolean showData) throws NotFoundException,BadRequestException, UnknownException, ConnectionException;

    @Operation(summary = "Actualizar el estado a cerrado de una apertura", description = "Actualiza el estado a cerrado de una apertura", tags = {"Configuracion Apertura caja"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Se actualizó con exito el estado a cerradó de una apertura")})
    ResponseEntity<Void> updateStatus(@PathVariable Long id) throws NotFoundException,BadRequestException, UnknownException, ConnectionException;
}
