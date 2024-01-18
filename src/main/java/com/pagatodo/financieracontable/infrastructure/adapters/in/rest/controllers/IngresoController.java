package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.IngresoApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.IngresoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.IngresoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.IngresoRqVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.ingreso.IngresoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.ingreso.IngresoUseCase;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.ingreso}")
public class IngresoController implements IngresoApi {

    private final IngresoUseCase ingresoUseCase;

    private final IngresoMapperApi ingresoMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<IngresoResponse> save(@RequestBody IngresoRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        var result = ingresoUseCase.create(ingresoMapperApi.requestToDomain(request));
        return new ResponseEntity<>(ingresoMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    @Override
    public ResponseEntity<Void> updateCancellationReason(@PathVariable Integer id, @RequestParam("cancellationReason") String cancellationReason) throws NotFoundException, BusinessException {
        ingresoUseCase.updateCancellationReason(id, cancellationReason);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/voucher")
    @Override
    public ResponseEntity<FileReportResponse> generateVoucher(IngresoRqVoucher ingresoRqVoucher) throws JRException {
        var result = ingresoUseCase.generateVoucher(ingresoMapperApi.requestVoucherToVoucher(ingresoRqVoucher));
        return new ResponseEntity<>(ingresoMapperApi.domainToResponse(result), HttpStatus.OK);
    }
}
