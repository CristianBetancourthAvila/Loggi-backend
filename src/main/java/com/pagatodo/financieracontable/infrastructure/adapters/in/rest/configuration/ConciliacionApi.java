package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.ConciliacionRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ConciliacionRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConciliacionFilterResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConciliacionResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

public interface ConciliacionApi {

    @Operation(summary = "Crear conciliacion", description = "Crea conciliacion", tags = {"Configuracion Conciliacion"})
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Conciliacion creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConciliacionResponse.class))),})
    ResponseEntity<ConciliacionResponse> create(ConciliacionRequest conciliacionRequest) throws ConciliacionBusinessException;

    @Operation(summary = "Lista filtrada de conciliacion", description = "Trae una lista paginada de las conciliaciones filtradas", tags = {"Configuracion Conciliacion"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Conciliaciones encontradas", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ConciliacionFilterResponse.class))) }),})
    ResponseEntity<PageResponse<List<ConciliacionFilterResponse>>> findWithFilter(ConciliacionRqFilter conciliacionRqFilter, @RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage, @RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip) throws ConciliacionBusinessException;
}
