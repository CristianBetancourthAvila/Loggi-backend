package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.filter.ProgramarPagoFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoControllerTest {

    @InjectMocks
    private ProgramarPagoController programarPagoController;

    @Mock
    private ProgramarPagoMapperApi programarPagoMapperApi;

    @Mock
    private CreatePPUseCase createPPUseCase;

    @Mock
    private GetPPByCriteriaUseCase getPPByCriteriaUseCase;

    @Mock
    private GetCajasByPPIdUseCase getCajasByPPIdUseCase;

    @Mock
    private UpdatePPUseCase updatePPUseCase;

    @Mock
    private UpdatePPStatusUseCase updatePPStatusUseCase;

    private static ProgramarPagoRequest programarPagoRequest;
    private static ProgramarPagoResponse programarPagoResponse;
    private static ProgramarPago programarPago;

    @BeforeEach
    void setUp() {
        programarPagoRequest = new ProgramarPagoRequest();
        programarPagoRequest.setId(1);
        programarPagoRequest.setParametrizacionConceptoId(1L);
        programarPagoRequest.setUsuarioTerceroId(BigInteger.ONE);
        programarPagoRequest.setAfectacionCartera(true);
        programarPagoRequest.setCodigoVendedor(123L);
        programarPagoRequest.setFechaAplicacion(LocalDate.now());
        programarPagoRequest.setRangoVigencia("2023-11-15");
        programarPagoRequest.setValor(1000L);
        programarPagoRequest.setObservacion("Observación de prueba");
        programarPagoRequest.setEstado(Estado.ACTIVO);
        programarPagoRequest.setCajaIds(List.of(BigInteger.valueOf(5)));

        programarPagoResponse = new ProgramarPagoResponse();
        programarPagoResponse.setId(1);
        programarPagoResponse.setParametrizacionConcepto(new ParametrizacionConcepto());
        programarPagoResponse.setUsuarioTerceroId(BigInteger.ONE);
        programarPagoResponse.setAfectacionCartera(true);
        programarPagoResponse.setFechaCreacion(LocalDate.now());
        programarPagoResponse.setCodigoVendedor(123L);
        programarPagoResponse.setFechaAplicacion(LocalDate.now());
        programarPagoResponse.setRangoVigencia("2023-11-15");
        programarPagoResponse.setValor(1000L);
        programarPagoResponse.setObservacion("Observación de prueba");
        programarPagoResponse.setEstado(Estado.ACTIVO);
        programarPagoResponse.setEgresoCaja(new EgresoCaja());

        programarPago = new ProgramarPago();
        programarPago.setId(1);
        programarPago.setParametrizacionConcepto(new ParametrizacionConcepto());
        programarPago.setUsuarioTerceroId(BigInteger.ONE);
        programarPago.setAfectacionCartera(true);
        programarPago.setFechaCreacion(LocalDate.now());
        programarPago.setCodigoVendedor(123L);
        programarPago.setFechaAplicacion(LocalDate.now());
        programarPago.setRangoVigencia("2023-11-15");
        programarPago.setValor(1000L);
        programarPago.setObservacion("Observación de prueba");
        programarPago.setEstado(Estado.ACTIVO);
        programarPago.setEgresoCaja(new EgresoCaja());

        programarPagoController = new ProgramarPagoController(programarPagoMapperApi, createPPUseCase, getPPByCriteriaUseCase, getCajasByPPIdUseCase, updatePPUseCase, updatePPStatusUseCase);
    }

    @Test
    @DisplayName("Test for the create controller method")
    void createProgramarPago() throws AperturaCajaNotFoundException, AperturaCajaBusinessException, CajaIdNotFoundException {
        when(createPPUseCase.create(programarPago, List.of(BigInteger.valueOf(5))))
                .thenReturn(programarPago);
        when(programarPagoMapperApi.requestToDomain(programarPagoRequest))
                .thenReturn(programarPago);
        when(programarPagoMapperApi.domainToResponse(programarPago))
                .thenReturn(programarPagoResponse);
        ResponseEntity<ProgramarPagoResponse> responseEntity = programarPagoController.create(programarPagoRequest);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertDoesNotThrow(() -> PropertyTester.test(responseEntity.getBody()));
    }

    @Test
    @DisplayName("Test for findWithFilter method")
    void findProgramarPagoWithFilter() throws CajaNotFoundException {
        ProgramarPagoRQFilter filter = new ProgramarPagoRQFilter();
        filter.setConceptoEgreso(1L);
        filter.setFechaAplicacion(LocalDate.now());
        filter.setCondicion(Condicion.NO_PAGADO);
        ProgramarPago programarPago = new ProgramarPago();
        List<ProgramarPago> programarPagos = Collections.singletonList(programarPago);
        PageModel<List<ProgramarPago>> pageModel = new PageModel<>(programarPagos, BigInteger.valueOf(programarPagos.size()));
        when(getPPByCriteriaUseCase.findWithFiler(any(ProgramarPagoFilter.class), anyInt(), anyInt()))
                .thenReturn(pageModel);
        when(getCajasByPPIdUseCase.findCajasByPPId(any(Integer.class))).thenReturn(Collections.singletonList(new Caja()));
        ProgramarPagoFilterResponse programarPagoFilterResponse = new ProgramarPagoFilterResponse();
        programarPagoFilterResponse.setId(0);
        when(programarPagoMapperApi.domainsToFilterResponse(pageModel.data())).thenReturn(List.of(programarPagoFilterResponse));
        when(programarPagoMapperApi.requestFilterToDomain(filter)).thenReturn(new ProgramarPagoFilter());
        ResponseEntity<PageResponse<List<ProgramarPagoFilterResponse>>> responseEntity = programarPagoController.findWithFilter(filter, 10, 0);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getData().size());
        List<ProgramarPagoFilterResponse> responseList = responseEntity.getBody().getData();
        for (ProgramarPagoFilterResponse response : responseList) {
            assertNotNull(response.getCajaList());
            assertEquals(1, response.getCajaList().size());
        }
    }

    @Test
    @DisplayName("Test for the update controller method")
    void updateProgramarPago() throws Exception {
        ProgramarPagoUpdateRequest programarPagoUpdateRequest = new ProgramarPagoUpdateRequest();
        programarPagoUpdateRequest.setCajaIds(List.of(BigInteger.ONE));
        ProgramarPago programarPago = new ProgramarPago();
        when(updatePPUseCase.update(programarPago, List.of(BigInteger.ONE))).thenReturn(programarPago);
        when(programarPagoMapperApi.requestUpdateToDomain(any(ProgramarPagoUpdateRequest.class)))
                .thenReturn(programarPago);
        when(programarPagoMapperApi.domainToResponse(any(ProgramarPago.class)))
                .thenReturn(new ProgramarPagoResponse());
        ResponseEntity<ProgramarPagoResponse> responseEntity = programarPagoController.update(programarPagoUpdateRequest);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertDoesNotThrow(() -> PropertyTester.test(responseEntity.getBody()));
    }

    @Test
    @DisplayName("Test for the updateStatus controller method")
    void updateProgramarPagoStatus() throws ProgramarPagoNotFoundException {
        doNothing().when(updatePPStatusUseCase).updateStatus(any());
        ResponseEntity<Void> responseEntity = programarPagoController.updateStatus(1);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
