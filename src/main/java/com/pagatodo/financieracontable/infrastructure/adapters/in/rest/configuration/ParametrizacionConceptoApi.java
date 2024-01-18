package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.rest.payload.ApiErrorPayload;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.enums.ExportType;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ParametrizacionConceptoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConceptoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ParametrizacionConceptoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;

@Tag(name = "Parametrizacion Concepto CC", description = "Servicios relacionados con parametrizacion de concepto CC")
public interface ParametrizacionConceptoApi {

    @Operation(summary = "Lista de parametrizacion de concepto CC", description = "Lista de parametrizacion de concepto cuentas contables")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de parametrizacion de concepto", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PageResponse.class))) }),})
    ResponseEntity<PageResponse<List<ParametrizacionConceptoResponse>>> findWithFilter(
    		@RequestParam(name = "codigoNombreConcepto", required = false)  String codigoNombreConcepto,
    		@RequestParam(name = "tipo", required = false)  String tipo,
    		@RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage,
    		@RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip);
   
    @Operation(summary = "Crea una parametrizacion de concepto CC", description = "Crea una parametrizacion de concepto cuentas contables")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "ParametrizacionConcepto actualizada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParametrizacionConceptoResponse.class))),})
    ResponseEntity<ParametrizacionConceptoResponse> create(ParametrizacionConceptoRequest request);
    
	@Operation(summary = "Actualiza parametrizacion de concepto CC", description = "Permite actualizar un parametrizacion de concepto CC")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro actualizado", content = @Content(schema = @Schema(implementation = ParametrizacionConceptoResponse.class))),
			@ApiResponse(responseCode = "404", description = "En caso que el parametrizacion de concepto CC no exista", content = @Content(schema = @Schema(implementation = ApiErrorPayload.class))),
	})
	ResponseEntity<ParametrizacionConceptoResponse> update(Long id, ParametrizacionConceptoRequest request) throws NotFoundException;
    
	@Operation(summary = "Actualiza el estado de parametrizacion de concepto CC", description = "Permite activar/inactivar parametrizacion de concepto CC")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro actualizado", content = @Content(schema = @Schema(implementation = ParametrizacionConceptoResponse.class))),
			@ApiResponse(responseCode = "404", description = "En caso que parametrizacion de concepto CC a cambiar estado no exista", content = @Content(schema = @Schema(implementation = ApiErrorPayload.class))),
	})
	ResponseEntity<ParametrizacionConceptoResponse> updateStatus(Long id, Estado estado) throws NotFoundException;
	
	
    @Operation(summary = "Lista de concepto parametrizadas", description = "Lista de concepto parametrizadas")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de concepto", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ConceptoResponse.class))) }),})
    ResponseEntity<List<ConceptoResponse>> findConceptoWithFilter(
    		@RequestParam(name = "codigo", required = false) Long codigo,
    		@RequestParam(name = "nombre", required = false)  String nombre);
    
    
    @Operation(summary = "Generar reporte", description = "Genera reporte en excel, csv o pdf")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Generar reporte", content = @Content(schema = @Schema(implementation = FileReportResponse.class))),})
    ResponseEntity<FileReportResponse> report(
    		@RequestParam(name = "type", required = true)  ExportType type,
    		@RequestParam(name = "codigoNombreConcepto", required = false)  String codigoNombreConcepto,
    		@RequestParam(name = "tipo", required = false)  String tipo) throws JRException;
}
