package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.TrasladoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoFilterByDateResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoSendReceiveResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface TrasladoApi {
    @Operation(summary = "Crear traslado", description = "Crea un registro de traslado", tags = {"Configuracion Traslado"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Traslado creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrasladoResponse.class)))})
    ResponseEntity<TrasladoResponse> save(@RequestBody TrasladoRequest request) throws BusinessException;

    @Operation(summary = "Lista de traslados", description = "Lista de traslados por fecha", tags = {"Configuracion Traslado"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de traslados", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TrasladoFilterByDateResponse.class))) }),})
    ResponseEntity<PageResponse<List<TrasladoFilterByDateResponse>>> findTrasladosByDate(@RequestParam(name = "date", required = false) LocalDate date, @RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage, @RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip);

    @Operation(summary = "Lista de traslados para enviar o recibir por caja", description = "Lista de traslados a enviar o recibir por caja", tags = {"Configuracion Traslado"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de traslados a enviar o recibir por la caja ingresada", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TrasladoSendReceiveResponse.class))) }),})
    ResponseEntity<PageResponse<List<TrasladoSendReceiveResponse>>> findSendReceiveTrasladosByCaja(@RequestParam(name = "cajaId") BigInteger cajaId, @RequestParam(name = "send") Boolean send, @RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage, @RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip);
}
