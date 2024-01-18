package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.AnulacionApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.AnulacionRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.AnulacionResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.AnulacionRqVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.anulacion.AnulacionMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.anulacion.AnulacionUseCase;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.anulacion}")
public class AnulacionController implements AnulacionApi {

    private final AnulacionUseCase anulacionUseCase;

    private final AnulacionMapperApi anulacionMapperApi;

    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<AnulacionResponse>>> findAnulacionesByCriteria( AnulacionRqFilter anulacionRqFilter, Integer rowsPerPage, Integer skip){
        var paginatedResul = anulacionUseCase.findAnulacionesByCriteria(anulacionMapperApi.requestFilterToDomain(anulacionRqFilter), rowsPerPage, skip);
        PageResponse<List<AnulacionResponse>> resultPage = new PageResponse<>();
        resultPage.setData(anulacionMapperApi.domainsToResponses(paginatedResul.data()));
        resultPage.setTotal(paginatedResul.total());
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    @Override
    public ResponseEntity<Void> updateAuthorizerUser(Integer id, Integer userId) throws NotFoundException {
        anulacionUseCase.updateAuthorizerUser(id, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/voucher")
    @Override
    public ResponseEntity<FileReportResponse> generateVoucher(AnulacionRqVoucher anulacionRqVoucher) throws JRException {
        var result = anulacionUseCase.generateVoucher(anulacionMapperApi.requestVoucherToVoucher(anulacionRqVoucher));
        return new ResponseEntity<>(anulacionMapperApi.domainToResponse(result), HttpStatus.OK);
    }


}
