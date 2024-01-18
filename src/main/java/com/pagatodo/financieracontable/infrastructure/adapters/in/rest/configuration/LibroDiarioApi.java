package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioBusinessException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.LibroDiarioRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.LibroDiarioResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

public interface LibroDiarioApi {

    @Operation(summary = "Libro diario paginado", description = "Libro diario paginado por criterios", tags = {"Libro diario"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Libro diario", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PageResponse.class))) }),})
    ResponseEntity<PageResponse<List<LibroDiarioResponse>>> findWithFilter(@Valid LibroDiarioRqFilter libroDiarioRqFilter, @RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage, @RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip) throws LibroDiarioBusinessException;

}
