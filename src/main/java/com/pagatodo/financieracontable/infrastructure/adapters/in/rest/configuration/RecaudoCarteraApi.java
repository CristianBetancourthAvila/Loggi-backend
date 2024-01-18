package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.RecaudoCarteraRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.RecaudoCarteraResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.RecaudoCarteraRqVoucher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface RecaudoCarteraApi {
    @Operation(summary = "Crear recaudo cartera", description = "Crea un registro de recaudo para una cartera", tags = {"Configuracion Recaudo Cartera"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Se creo con exito el registro de recaudo cartera", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecaudoCarteraResponse.class)))})
    ResponseEntity<RecaudoCarteraResponse> save(@RequestBody RecaudoCarteraRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;

    @Operation(summary = "Generar comprobante", description = "Genera comprobante de recaudo cartera",tags = {"Configuracion Recaudo Cartera"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se genero el comprobante", content = @Content(schema = @Schema(implementation = FileReportResponse.class))),})
    ResponseEntity<FileReportResponse> generateVoucher(RecaudoCarteraRqVoucher recaudoCarteraRqVoucher) throws JRException;
}
