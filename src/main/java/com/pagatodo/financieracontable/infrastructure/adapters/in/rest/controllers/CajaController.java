package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.CajaRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CajaUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaInfoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaMatchResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.caja.CajaMapperApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.CajaApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CajaRequest;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.CreateAllCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetByDateMensualUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetByDateSemanalUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByCriteriaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByMacUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByUserIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajasByIdSellerUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetAllByMatchAndStatusUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.UpdateCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.UpdateStatusCajaUseCase;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(CajaController.CAJA)
public class CajaController implements CajaApi {
    public static final String CAJA = "/${request-mapping.controller.gestion-caja}";
    private final CreateAllCajaUseCase createAllCajaUseCase;
    private final UpdateCajaUseCase updateCajaUseCase;
    private final UpdateStatusCajaUseCase updateStatusCajaUseCase;
    private final GetCajaByCriteriaUseCase getCajaByCriteriaUseCase;
    private final GetByDateSemanalUseCase getByDateSemanalUseCase;
    private final GetByDateMensualUseCase getByDateMensualUseCase;
    private final GetCajasByIdSellerUseCase getCajasByIdSellerUseCase;
    private final GetAllByMatchAndStatusUseCase getAllByMatchAndStatusUseCase;
    private final GetCajaByUserIdUseCase getCajaByUserIdUseCase;
    private final GetCajaByIdUseCase getCajaByIdUseCase;
    private final GetCajaByMacUseCase getCajaByMacUseCase;
    private final CajaMapperApi cajaMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<List<CajaResponse>> create(@RequestBody List<CajaRequest> cajaRequest) {
        var result = createAllCajaUseCase.saveAll(cajaMapperApi.requestsToDomains(cajaRequest));
        return new ResponseEntity<>(cajaMapperApi.domainsToResponses(result) , HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<CajaResponse> update(@RequestBody CajaUpdateRequest cajaUpdateRequest) throws NotFoundException {
        var result = updateCajaUseCase.update(cajaMapperApi.requestToDomain(cajaUpdateRequest));
        return new ResponseEntity<>(cajaMapperApi.domainToResponse(result) , HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<Void> updateStatus(@PathVariable BigInteger id) throws CajaIdNotFoundException {
        updateStatusCajaUseCase.updateStatus(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<CajaResponse>>> findWithFilter(CajaRqFilter cajaRqFilter, Integer rowsPerPage, Integer skip) throws CajaNotFoundException {
        var paginatedResul = getCajaByCriteriaUseCase.findWithFiler(cajaMapperApi.requestFilterToDomain(cajaRqFilter), rowsPerPage, skip);
        PageResponse<List<CajaResponse>> resultPage = new PageResponse<>();
        resultPage.setData(cajaMapperApi.domainsToResponses(paginatedResul.data()));
        resultPage.setTotal(paginatedResul.total());
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

    @GetMapping("/week")
    @Override
    public ResponseEntity<FileReportResponse> findByDateSemanal(LocalDate from, LocalDate to) throws CajaNotFoundException, JRException, FileNotFoundException {
        return new ResponseEntity<>(cajaMapperApi.fileDomainToResponse(getByDateSemanalUseCase.findByDateSemanal(from, to)), HttpStatus.OK);
    }

    @GetMapping("/month")
    @Override
    public ResponseEntity<FileReportResponse> findByDateMensual(LocalDate date) throws CajaNotFoundException, IOException, JRException {
        return new ResponseEntity<>(cajaMapperApi.fileDomainToResponse(getByDateMensualUseCase.findByDateMensual(date)), HttpStatus.OK);
    }

    @GetMapping("/seller")
    @Override
    public ResponseEntity<List<CajaResponse>> findByIdSeller(Long idSeller) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        return new ResponseEntity<>(cajaMapperApi.domainsToResponses(getCajasByIdSellerUseCase.findCajasByIdSeller(idSeller)), HttpStatus.OK);
    }

    @GetMapping("/match")
    @Override
    public ResponseEntity<List<CajaMatchResponse>> findByMatchesAndStatus(String filterText, Estado status) {
        return new ResponseEntity<>(cajaMapperApi.domainsToMatchResponses(getAllByMatchAndStatusUseCase.findAllByMatchesAndStatus(filterText, status)), HttpStatus.OK);
    }

    @GetMapping("/auth/seller")
    @Override
    public ResponseEntity<CajaResponse> findCajaByIdSeller(Long idSeller) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        return new ResponseEntity<>(cajaMapperApi.domainToResponse(getCajaByUserIdUseCase.findCajaByUserId(idSeller)), HttpStatus.OK);
    }

    @GetMapping("/caja")
    @Override
    public ResponseEntity<CajaResponse> findCajaById(BigInteger id){
        return new ResponseEntity<>(cajaMapperApi.domainToResponse(getCajaByIdUseCase.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/mac")
    @Override
    public ResponseEntity<CajaInfoResponse> findCajaByMac(String mac, Long companyId) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        return new ResponseEntity<>(cajaMapperApi.domainToCajaInfoResponse(getCajaByMacUseCase.findByMac(mac, companyId)), HttpStatus.OK);
    }
}
