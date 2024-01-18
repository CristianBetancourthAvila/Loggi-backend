package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.application.usecases.egresocaja.GetAllPPByTerceroIdUseCaseImpl;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.EgresoCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.EgresoCajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.egresocaja.EgresoCajaMapperApi;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.programarpago.ProgramarPagoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja.CreateEgresoCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja.UpdateMotivoECUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EgresoCajaControllerTest {

    @InjectMocks
    private EgresoCajaController egresoCajaController;

    @Mock
    private EgresoCajaMapperApi egresoCajaMapperApi;

    @Mock
    private CreateEgresoCajaUseCase createEgresoCajaUseCase;

    @Mock
    private UpdateMotivoECUseCase updateMotivoECUseCase;

    @Mock
    private GetAllPPByTerceroIdUseCaseImpl getAllPPByTerceroIdUseCaseImpl;

    private static EgresoCajaRequest egresoCajaRequest;
    private static EgresoCajaResponse egresoCajaResponse;
    private static EgresoCaja egresoCaja;

    private ProgramarPagoMapperApi programarPagoMapperApi;

    @BeforeEach
    void setUp() {
        egresoCajaRequest = new EgresoCajaRequest();
        egresoCajaRequest.setId(1);
        egresoCajaRequest.setUsuarioTerceroId(BigInteger.ONE);
        egresoCajaRequest.setProgramarPagoId(3);
        egresoCajaRequest.setMotivoAnulacion("Motivo de Anulación");

        egresoCajaResponse = new EgresoCajaResponse();
        egresoCajaResponse.setId(1);
        egresoCajaResponse.setUsuarioTerceroId(BigInteger.ONE);
        egresoCajaResponse.setProgramarPago(new ProgramarPago());
        egresoCajaResponse.setMotivoAnulacion("Motivo de Anulación");
        egresoCajaResponse.setFechaCreacion(LocalDate.of(2023, 10, 2));

        egresoCaja = new EgresoCaja();
        egresoCaja.setId(1);
        egresoCaja.setUsuarioTerceroId(BigInteger.ONE);
        egresoCaja.setProgramarPago(new ProgramarPago());
        egresoCaja.setMotivoAnulacion("Motivo de Anulación");
        egresoCaja.setFechaCreacion(LocalDate.of(2023, 10, 2));

        programarPagoMapperApi = Mockito.mock(ProgramarPagoMapperApi.class);
        egresoCajaController = new EgresoCajaController(egresoCajaMapperApi, programarPagoMapperApi, createEgresoCajaUseCase, updateMotivoECUseCase, getAllPPByTerceroIdUseCaseImpl);
    }

    @Test
    @DisplayName("Test for the create controller method")
    void createEgresoCaja() throws BusinessException, NotFoundException {
        Mockito.when(egresoCajaMapperApi.requestToDomain(egresoCajaRequest))
                .thenReturn(egresoCaja);
        Mockito.when(createEgresoCajaUseCase.create(egresoCaja))
                .thenReturn(egresoCaja);
        Mockito.when(egresoCajaMapperApi.domainToResponse(egresoCaja))
                .thenReturn(egresoCajaResponse);
        ResponseEntity<EgresoCajaResponse> responseEntity = egresoCajaController.create(egresoCajaRequest);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertDoesNotThrow(() -> PropertyTester.test(responseEntity.getBody()));
    }

    @Test
    @DisplayName("Test for findByTerceroId method")
    void findByTerceroIdTest() throws TerceroNotFoundException {
        BigInteger terceroId = BigInteger.ONE;
        int rowsPerPage = 10;
        int skip = 0;
        PageModel<List<ProgramarPago>> pageResponse = new PageModel<>(Collections.emptyList(), BigInteger.ZERO);
        Mockito.when(getAllPPByTerceroIdUseCaseImpl.findByTerceroId(terceroId, rowsPerPage, skip))
                .thenReturn(pageResponse);
        ResponseEntity<PageResponse<List<ProgramarPagoResponse>>> responseEntity =
                egresoCajaController.findByTerceroId(terceroId, rowsPerPage, skip);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for updateStatus method")
    void updateStatusTest() throws NotFoundException, BusinessException {
        int id = 1;
        String motivoAnulacion = "Motivo de anulación";
        EgresoCaja egresoCaja1 = new EgresoCaja();
        egresoCaja1.setMotivoAnulacion(motivoAnulacion);
        PageResponse<List<ProgramarPagoResponse>> resultPage = new PageResponse<>();
        resultPage.setTotal(BigInteger.ONE);
        Mockito.doNothing().when(updateMotivoECUseCase).updateMotivo(id, egresoCaja1);
        ResponseEntity<Void> responseEntity =
                egresoCajaController.updateStatus(id, egresoCaja1);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
