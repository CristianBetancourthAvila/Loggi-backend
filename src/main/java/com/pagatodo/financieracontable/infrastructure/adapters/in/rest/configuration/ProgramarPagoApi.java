package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableUnknownException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.ProgramarPagoRQFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ProgramarPagoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ProgramarPagoUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoFilterResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProgramarPagoApi {

    @Operation(summary = "Crear programar pago", description = "Crear programación de pago", tags = {"Configuracion programar pago"})
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Programar pago creado exitosamente", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProgramarPagoResponse.class))) }),})
    ResponseEntity<ProgramarPagoResponse> create(@Valid @RequestBody ProgramarPagoRequest programarPagoRequest) throws AperturaCajaBusinessException, AperturaCajaNotFoundException, CajaIdNotFoundException;

    @Operation(summary = "Actualizar un programar pago", description = "Actualizar programar pago en sus campos permitidos", tags = {"Configuracion programar pago"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Programar pago actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProgramarPagoResponse.class))),})
    ResponseEntity<ProgramarPagoResponse> update(@Valid @RequestBody ProgramarPagoUpdateRequest programarPagoUpdateRequest) throws NotFoundException, FinancieraContableUnknownException;

    @Operation(summary = "Lista filtrada de programar pago", description = "Trae una lista de los programar pago por un filtro", tags = {"Configuracion programar pago"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de pagos", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProgramarPagoFilterResponse.class))) }),})
    ResponseEntity<PageResponse<List<ProgramarPagoFilterResponse>>> findWithFilter(ProgramarPagoRQFilter programarPagoRQFilter, @RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage, @RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip) throws CajaNotFoundException;

    @Operation(summary = "Actualizar estado de un programar pago", description = "Actualizar estado de un programar pago por su id", tags = {"Configuracion programar pago"})
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Estado de programar pago actualizado correctamente"),})
    ResponseEntity<Void> updateStatus(@PathVariable Integer id) throws ProgramarPagoNotFoundException;
}
