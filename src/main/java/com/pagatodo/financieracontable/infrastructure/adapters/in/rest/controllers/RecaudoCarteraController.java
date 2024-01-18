package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.RecaudoCarteraApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.RecaudoCarteraRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.RecaudoCarteraResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.RecaudoCarteraRqVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.recaudocartera.RecaudoCarteraMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.recaudocartera.RecaudoCarteraUseCase;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.recaudo-cartera}")
public class RecaudoCarteraController implements RecaudoCarteraApi {

    private final RecaudoCarteraMapperApi recaudoCarteraMapperApi;

    private final RecaudoCarteraUseCase recaudoCarteraUseCase;

    @PostMapping
    @Override
    public ResponseEntity<RecaudoCarteraResponse> save(@RequestBody RecaudoCarteraRequest request) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        var result = recaudoCarteraUseCase.create(recaudoCarteraMapperApi.requestToDomain(request));
        return new ResponseEntity<>(recaudoCarteraMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @GetMapping("/voucher")
    @Override
    public ResponseEntity<FileReportResponse> generateVoucher(RecaudoCarteraRqVoucher recaudoCarteraRqVoucher) throws JRException {
        var result  = recaudoCarteraUseCase.generateVoucher(recaudoCarteraMapperApi.requestVoucherToVoucher(recaudoCarteraRqVoucher));

        return new ResponseEntity<>(recaudoCarteraMapperApi.domainToResponse(result), HttpStatus.OK);
    }
}
