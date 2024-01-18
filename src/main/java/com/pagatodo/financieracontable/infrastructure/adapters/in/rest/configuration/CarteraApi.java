package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CarteraRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CarteraUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CarteraResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CarteraApi {
    @Operation(summary = "Crear cartera", description = "Crea un registro de cartera", tags = {"Configuracion Cartera"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Se creo con exito el registro de cartera", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarteraResponse.class)))})
    ResponseEntity<CarteraResponse> save(@RequestBody CarteraRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;

    @Operation(summary = "Obtener cartera por vendedor", description = "Obtiene una cartera por vendedor id", tags = {"Configuracion Cartera"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cartera encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarteraResponse.class)))})
    ResponseEntity<CarteraResponse> findCarteraByVendedorId(@PathVariable Integer sellerId) throws NotFoundException;

    @Operation(summary = "Actualizar una cartera", description = "Actualiza los valores de cartera", tags = {"Configuracion Cartera"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cartera actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarteraResponse.class)))})
    ResponseEntity<CarteraResponse> update(@RequestBody CarteraUpdateRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;
}
