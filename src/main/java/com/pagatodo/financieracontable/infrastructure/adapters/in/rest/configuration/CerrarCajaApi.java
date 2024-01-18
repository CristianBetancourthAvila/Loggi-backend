package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CerrarCajaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CerrarCajaApi {
    @Operation(summary = "Crear cierre caja", description = "Crea un registro de cierre de caja", tags = {"Configuracion Cerrar caja"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Se creo con exito el registro de cierre de caja")})
    ResponseEntity<Void> save(@RequestBody CerrarCajaRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;
}
