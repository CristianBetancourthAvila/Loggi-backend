package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.financieracontable.FinancieraContableUnknownException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.configuration.ProgramarPagoApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.ProgramarPagoRQFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ProgramarPagoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ProgramarPagoUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoFilterResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.programarpago.ProgramarPagoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajasByPPIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.programarpago.CreatePPUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.programarpago.GetPPByCriteriaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.programarpago.UpdatePPStatusUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.programarpago.UpdatePPUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ProgramarPagoController.PROGRAMARPAGO)
public class ProgramarPagoController implements ProgramarPagoApi {

    public static final String PROGRAMARPAGO = "/${request-mapping.controller.programar-pago}";
    private final ProgramarPagoMapperApi programarPagoMapperApi;
    private final CreatePPUseCase createPPUseCase;
    private final GetPPByCriteriaUseCase getPPByCriteriaUseCase;
    private final GetCajasByPPIdUseCase getCajasByPPIdUseCase;
    private final UpdatePPUseCase updatePPUseCase;
    private final UpdatePPStatusUseCase updatePPStatusUseCase;

    @PostMapping
    @Override
    public ResponseEntity<ProgramarPagoResponse> create(ProgramarPagoRequest programarPagoRequest) throws AperturaCajaBusinessException, CajaIdNotFoundException, AperturaCajaNotFoundException {
        ProgramarPago programarPago = createPPUseCase.create(programarPagoMapperApi.requestToDomain(programarPagoRequest), programarPagoRequest.getCajaIds());
        return new ResponseEntity<>(programarPagoMapperApi.domainToResponse(programarPago), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<ProgramarPagoResponse> update(ProgramarPagoUpdateRequest programarPagoUpdateRequest) throws NotFoundException, FinancieraContableUnknownException {
        ProgramarPago programarPago = updatePPUseCase.update(programarPagoMapperApi.requestUpdateToDomain(programarPagoUpdateRequest), programarPagoUpdateRequest.getCajaIds());
        return new ResponseEntity<>(programarPagoMapperApi.domainToResponse(programarPago), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<ProgramarPagoFilterResponse>>> findWithFilter(ProgramarPagoRQFilter programarPagoRQFilter, Integer rowsPerPage, Integer skip) throws CajaNotFoundException {
        var paginatedResult = getPPByCriteriaUseCase.findWithFiler(programarPagoMapperApi.requestFilterToDomain(programarPagoRQFilter), rowsPerPage, skip);
        PageResponse<List<ProgramarPagoFilterResponse>> resultPage = new PageResponse<>();
        List<ProgramarPagoFilterResponse> programarPagoResponses = programarPagoMapperApi.domainsToFilterResponse(paginatedResult.data());
        assignCajas(programarPagoResponses, 0);
        resultPage.setData(programarPagoResponses);
        resultPage.setTotal(paginatedResult.total());
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<Void> updateStatus(Integer id) throws ProgramarPagoNotFoundException {
        updatePPStatusUseCase.updateStatus(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void assignCajas(List<ProgramarPagoFilterResponse> programarPagoResponses, int index) throws CajaNotFoundException {
        if (index >= programarPagoResponses.size()) {
            return;
        }
        ProgramarPagoFilterResponse programarPagoResponse = programarPagoResponses.get(index);
        List<Caja> cajasForThisProgramarPago = getCajasByPPIdUseCase.findCajasByPPId(programarPagoResponse.getId());
        programarPagoResponse.setCajaList(cajasForThisProgramarPago);
        assignCajas(programarPagoResponses, index + 1);
    }
}
