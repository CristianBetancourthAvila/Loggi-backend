package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.EgresoCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.EgresoCajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

public interface EgresoCajaApi {
    @Operation(summary = "Crear egreso caja", description = "Crea egreso caja", tags = {"Configuracion egreso caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Egreso caja creado", content ={ @Content(mediaType = "application/json", schema = @Schema(implementation = EgresoCajaResponse.class)) }),})
    ResponseEntity<EgresoCajaResponse> create(@RequestBody EgresoCajaRequest egresoCajaRequests) throws BusinessException, NotFoundException;

    @Operation(summary = "Actualizar motivo anulacion de un egreso caja", description = "Actualizar motivo anulacion de un egreso caja por su id", tags = {"Configuracion egreso caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Motivo anulación actualizado correctamente"),})
    ResponseEntity<Void> updateStatus(@PathVariable Integer id, @RequestBody EgresoCaja egresoCaja) throws NotFoundException, BusinessException;

    @Operation(summary = "Lista de conceptos", description = "Lista de conceptos en base al programar pago", tags = {"Configuracion egreso caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de conceptos", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProgramarPagoResponse.class))) }),})
    ResponseEntity<PageResponse<List<ProgramarPagoResponse>>> findByTerceroId(@RequestParam(name = "terceroId") BigInteger terceroId, @RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage, @RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip) throws TerceroNotFoundException;

}
