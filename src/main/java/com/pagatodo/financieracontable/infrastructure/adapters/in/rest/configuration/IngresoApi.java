package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.IngresoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.IngresoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.IngresoRqVoucher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IngresoApi {

    @Operation(summary = "Crear ingreso", description = "Crea un registro de ingreso", tags = {"Configuracion Ingreso"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Crea ingreso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = IngresoResponse.class)))})
    ResponseEntity<IngresoResponse> save(@RequestBody IngresoRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;

    @Operation(summary = "Actualizar motivo de anulacion", description = "Actualiza el campo motivo anulacion", tags = {"Configuracion Ingreso"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Se actualizo el motivo de anulación con exito")})
    ResponseEntity<Void> updateCancellationReason(Integer id, String cancellationReason) throws NotFoundException, BusinessException;

    @Operation(summary = "Generar comprobante", description = "Genera comprobante de ingreso",tags = {"Configuracion Ingreso"})
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se genero el comprobante", content = @Content(schema = @Schema(implementation = FileReportResponse.class))),})
    ResponseEntity<FileReportResponse> generateVoucher(IngresoRqVoucher ingresoRqVoucher) throws JRException;
}
