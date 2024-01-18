package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.CajaRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CajaUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaInfoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaMatchResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Size;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface CajaApi {
    @Operation(summary = "Crear por lista de cajas", description = "Crea cajas por una lista", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Lista de cajas creadas", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CajaResponse.class))) }),})
    ResponseEntity<List<CajaResponse>> create(List<CajaRequest> cajaRequest);

    @Operation(summary = "Actualizar una caja", description = "Actualizar caja unicamente en sus campos permitidos", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Caja actualizada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CajaResponse.class))),})
    ResponseEntity<CajaResponse> update(CajaUpdateRequest cajaUpdateRequest) throws NotFoundException;

    @Operation(summary = "Actualizar estado de una caja", description = "Actualizar estado de una caja por su id", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Estado de caja actualizado correctamente"),})
    ResponseEntity<Void> updateStatus(@PathVariable BigInteger id) throws CajaIdNotFoundException;

    @Operation(summary = "Lista de cajas filtradas", description = "Lista de cajas filtradas por criterios", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de cajas", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CajaResponse.class))) }),})
    ResponseEntity<PageResponse<List<CajaResponse>>> findWithFilter(CajaRqFilter cajaRqFilter, @RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage,@RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip) throws CajaNotFoundException;

    @Operation(summary = "Lista de cajas por rango semanal", description = "Lista de cajas por rango semanal para reporte", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de cajas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FileReportResponse.class))),})
    ResponseEntity<FileReportResponse> findByDateSemanal(@RequestParam(name = "from") LocalDate from, @RequestParam(name = "to") LocalDate to) throws CajaNotFoundException, JRException, FileNotFoundException;

    @Operation(summary = "Lista de cajas por año y mes", description = "Lista de cajas por año y mes para reporte", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de cajas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FileReportResponse.class))),})
    ResponseEntity<FileReportResponse> findByDateMensual(@RequestParam(name = "date") LocalDate date) throws CajaNotFoundException, IOException, JRException;

    @Operation(summary = "Lista de cajas por id vendedor", description = "Lista de cajas por id vendedor", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de cajas", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CajaResponse.class))) }),})
    ResponseEntity<List<CajaResponse>> findByIdSeller(@RequestParam(name = "idSeller") Long idSeller) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;

    @Operation(summary = "Lista de cajas por coincidencia y estado", description = "Lista de cajas por coincidencia de string para el codigo caja, el nombre caja y el estado", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de cajas", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CajaMatchResponse.class))) }),})
    ResponseEntity<List<CajaMatchResponse>> findByMatchesAndStatus(@RequestParam(name = "filterText", required = false) @Size(min = 3, message = "filterText must have at least 3 characters") String filterText, @RequestParam(name = "status", required = false) Estado status);

    @Operation(summary = "Caja por id usuario", description = "Caja por id usuario", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Caja encontrada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CajaResponse.class))),})
    ResponseEntity<CajaResponse> findCajaByIdSeller(@RequestParam(name = "idSeller") Long idSeller) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;

    @Operation(summary = "Caja por id", description = "Caja por id", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Caja encontrada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CajaResponse.class))),})
    ResponseEntity<CajaResponse> findCajaById(@RequestParam(name = "id") BigInteger id);

    @Operation(summary = "Caja por Mac", description = "Encontrar Caja por la Mac", tags = {"Configuracion Caja"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Caja encontrada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CajaInfoResponse.class))),})
    ResponseEntity<CajaInfoResponse> findCajaByMac(@RequestParam(name = "mac") String mac, @RequestParam(name = "companyId") Long companyId) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;
}
