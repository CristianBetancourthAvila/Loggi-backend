package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.AnulacionRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.AnulacionResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.AnulacionRqVoucher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AnulacionApi {

    @Operation(summary = "Lista de anulaciones", description = "Lista de anulaciones filtradas por criterios", tags = {"Configuracion Anulacion"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de anulaciones", content ={ @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AnulacionResponse.class))) }),})
    ResponseEntity<PageResponse<List<AnulacionResponse>>> findAnulacionesByCriteria(AnulacionRqFilter anulacionRqFilter, @RequestParam(name = "paginador.rowsPage", defaultValue = "10")  Integer rowsPerPage, @RequestParam(name = "paginador.skip", defaultValue = "0") Integer skip);

    @Operation(summary = "Actualizar anulacion", description = "Actualiza el autorizador id y el estado a anulado", tags = {"Configuracion Anulacion"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Se actualizo el autorizador id y el estado a anulado con exito"),})
    ResponseEntity<Void> updateAuthorizerUser(@PathVariable Integer id, @RequestParam("userId") Integer userId) throws NotFoundException;

    @Operation(summary = "Generar comprobante", description = "Genera comprobante de anulación",tags = {"Configuracion Anulacion"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se genero el comprobante", content = @Content(schema = @Schema(implementation = FileReportResponse.class))),})
    ResponseEntity<FileReportResponse> generateVoucher(AnulacionRqVoucher anulacionRqVoucher) throws JRException;
}
