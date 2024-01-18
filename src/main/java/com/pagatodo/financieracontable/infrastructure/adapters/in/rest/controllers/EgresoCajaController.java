package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.EgresoCajaApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.EgresoCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.EgresoCajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.egresocaja.EgresoCajaMapperApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.programarpago.ProgramarPagoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja.CreateEgresoCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja.GetAllPPByTerceroIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja.UpdateMotivoECUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(EgresoCajaController.EGRESO_CAJA)
public class EgresoCajaController implements EgresoCajaApi {

    public static final String EGRESO_CAJA = "/${request-mapping.controller.gestion-egreso-caja}";
    private final EgresoCajaMapperApi egresoCajaMapperApi;
    private final ProgramarPagoMapperApi programarPagoMapperApi;
    private final CreateEgresoCajaUseCase createEgresoCajaUseCase;
    private final UpdateMotivoECUseCase motivoECUseCase ;
    private final GetAllPPByTerceroIdUseCase getAllPPByTerceroIdUseCase;

    @PostMapping
    @Override
    public ResponseEntity<EgresoCajaResponse> create(EgresoCajaRequest egresoCajaRequests) throws BusinessException, NotFoundException {
        EgresoCaja egresoCaja = egresoCajaMapperApi.requestToDomain(egresoCajaRequests);
        return new ResponseEntity<>(egresoCajaMapperApi.domainToResponse(createEgresoCajaUseCase.create(egresoCaja)), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<Void> updateStatus(Integer id, EgresoCaja egresoCaja) throws NotFoundException, BusinessException {
        motivoECUseCase.updateMotivo(id, egresoCaja);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<ProgramarPagoResponse>>> findByTerceroId(BigInteger terceroId, Integer rowsPerPage, Integer skip) throws TerceroNotFoundException {
        var paginatedResul = getAllPPByTerceroIdUseCase.findByTerceroId(terceroId, rowsPerPage, skip);
        PageResponse<List<ProgramarPagoResponse>> resultPage = new PageResponse<>();
        resultPage.setData(programarPagoMapperApi.domainsToResponses(paginatedResul.data()));
        resultPage.setTotal(paginatedResul.total());
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }
}
