package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.CajaInfo;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.filter.CajaFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.CajaRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CajaUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaInfoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaMatchResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetAllByMatchAndStatusUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetByDateMensualUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetByDateSemanalUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByCriteriaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByMacUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByUserIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajasByIdSellerUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.UpdateCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.UpdateStatusCajaUseCase;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.caja.CajaMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.CreateAllCajaUseCase;

@ExtendWith(MockitoExtension.class)
class CajaControllerTest {

    @InjectMocks
    private CajaController cajaController;

    @Mock
    private CreateAllCajaUseCase createAllCajaUseCase;

    @Mock
    private UpdateCajaUseCase updateCajaUseCase;

    @Mock
    private UpdateStatusCajaUseCase updateStatusCajaUseCase;

    @Mock
    private GetCajaByCriteriaUseCase getCajaByCriteriaUseCase;

    @Mock
    private GetByDateSemanalUseCase getByDateSemanalUseCase;

    @Mock
    private GetByDateMensualUseCase getByDateMensualUseCase;

    @Mock
    private GetCajasByIdSellerUseCase getCajasByIdSellerUseCase;

    @Mock
    private GetAllByMatchAndStatusUseCase getAllByMatchAndStatusUseCase;

    @Mock
    private GetCajaByUserIdUseCase getCajaByUserIdUseCase;

    @Mock
    private GetCajaByIdUseCase getCajaByIdUseCase;

    @Mock
    private GetCajaByMacUseCase getCajaByMacUseCase;

    @Mock
    private CajaMapperApi cajaMapperApi;

    private static CajaRequest request;
    private static CajaResponse response;
    private static Caja domain;

    private static CajaUpdateRequest cajaUpdateRequest;

    @BeforeAll
    static void configInitial() {
        request = new CajaRequest();
        request.setId(BigInteger.ONE);
        request.setPuntoVentaTerminalId(BigInteger.TWO);
        request.setCodigoCaja("CAJA001");
        request.setNombreCaja("Caja de Prueba");
        request.setCodigoDane(123456L);
        request.setMontoMaximoPago(10000L);
        request.setMontoMaximoGiro(5000L);
        request.setMontoMaximoBeps(2000L);
        request.setMontoMaximoBbva(3000L);
        request.setCantidadPapelBond(500L);
        request.setCantidadPapelTermico(1000L);
        request.setEstado(Estado.ACTIVO);

        response = new CajaResponse();
        response.setId(BigInteger.ONE);
        response.setPuntoVentaTerminalId(BigInteger.TWO);
        response.setCodigoCaja("CAJA001");
        response.setNombreCaja("Caja de Prueba");
        response.setCodigoDane(123456L);
        response.setMontoMaximoPago(10000L);
        response.setMontoMaximoGiro(5000L);
        response.setMontoMaximoBeps(2000L);
        response.setMontoMaximoBbva(3000L);
        response.setCantidadPapelBond(500L);
        response.setCantidadPapelTermico(1000L);
        response.setEstado(Estado.ACTIVO);

        domain = new Caja();
        domain.setId(BigInteger.ONE);
        domain.setPuntoVentaTerminalId(BigInteger.TWO);
        domain.setCodigoCaja("CAJA001");
        domain.setNombreCaja("Caja de Prueba");
        domain.setCodigoDane(123456L);
        domain.setMontoMaximoPago(10000L);
        domain.setMontoMaximoGiro(5000L);
        domain.setMontoMaximoBeps(2000L);
        domain.setMontoMaximoBbva(3000L);
        domain.setCantidadPapelBond(500L);
        domain.setCantidadPapelTermico(1000L);
        domain.setEstado(Estado.ACTIVO);

        cajaUpdateRequest = new CajaUpdateRequest();
        cajaUpdateRequest.setId(BigInteger.ONE);
        cajaUpdateRequest.setEstado(Estado.INACTIVO);
        cajaUpdateRequest.setCantidadPapelBond(1L);
        cajaUpdateRequest.setMontoMaximoBeps(1L);
        cajaUpdateRequest.setMontoMaximoBbva(1L);
        cajaUpdateRequest.setMontoMaximoGiro(1L);
        cajaUpdateRequest.setCantidadPapelTermico(1L);
        cajaUpdateRequest.setMontoMaximoPago(1L);

        cajaUpdateRequest = new CajaUpdateRequest();
        cajaUpdateRequest.setId(BigInteger.ONE);
        cajaUpdateRequest.setEstado(Estado.INACTIVO);
        cajaUpdateRequest.setCantidadPapelBond(1L);
        cajaUpdateRequest.setMontoMaximoBeps(1L);
        cajaUpdateRequest.setMontoMaximoBbva(1L);
        cajaUpdateRequest.setMontoMaximoGiro(1L);
        cajaUpdateRequest.setCantidadPapelTermico(1L);
        cajaUpdateRequest.setMontoMaximoPago(1L);
    }
    @Test
    @DisplayName("Test for the saveAll controller method")
    void create_All_Caja()  {
        List<CajaRequest> cajaRequests = List.of(request);
        List<Caja> cajaDomains = List.of(domain);
        List<CajaResponse> cajaResponses = List.of(response);
        when(createAllCajaUseCase.saveAll(Mockito.anyList()))
                .thenReturn(cajaDomains);
        when(cajaMapperApi.requestsToDomains(Mockito.anyList()))
                .thenReturn(cajaDomains);
        when(cajaMapperApi.domainsToResponses(Mockito.anyList()))
                .thenReturn(cajaResponses);
        ResponseEntity<List<CajaResponse>> responseEntity = cajaController.create(cajaRequests);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(cajaResponses, responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for the update controller method")
    void updateCaja() throws NotFoundException{
        when(updateCajaUseCase.update(any()))
                .thenReturn(any());
        when(cajaMapperApi.domainToResponse(domain))
                .thenReturn(response);
        ResponseEntity<CajaResponse> responseEntity = cajaController.update(cajaUpdateRequest);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for the updateStatus controller method")
    void updateStatusCaja() throws CajaIdNotFoundException {
        doNothing().when(updateStatusCajaUseCase).updateStatus(any());
        ResponseEntity<Void> responseEntity = cajaController.updateStatus(BigInteger.ONE);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Test for the findWithFilter controller method")
    void findWithFilter() throws CajaNotFoundException {
        CajaFilter cajaFilter = new CajaFilter();
        CajaRqFilter cajaRqFilter = new CajaRqFilter();
        Integer rowsPerPage = 10;
        Integer skip = 0;
        PageModel<List<Caja>> paginatedResult = new PageModel<>(List.of(domain), BigInteger.ONE);
        when(getCajaByCriteriaUseCase.findWithFiler(cajaFilter, rowsPerPage, skip))
                .thenReturn(paginatedResult);
        List<CajaResponse> cajaResponses = List.of(response);
        when(cajaMapperApi.requestFilterToDomain(any()))
                .thenReturn(cajaFilter);
        when(cajaMapperApi.domainsToResponses(any()))
                .thenReturn(cajaResponses);
        ResponseEntity<PageResponse<List<CajaResponse>>> responseEntity = cajaController.findWithFilter(cajaRqFilter, rowsPerPage, skip);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        PageResponse<List<CajaResponse>> resultPage = responseEntity.getBody();
        Assertions.assertEquals(cajaResponses, resultPage.getData());
        Assertions.assertEquals(BigInteger.ONE, resultPage.getTotal());
    }


    @Test
    @DisplayName("Test for the findByDateSemanal controller method")
    void findByDateSemanal() throws CajaNotFoundException, JRException, FileNotFoundException {
        LocalDate from = LocalDate.of(2023, 10, 1);
        LocalDate to = LocalDate.of(2023, 10, 7);
        when(getByDateSemanalUseCase.findByDateSemanal(from, to))
                .thenReturn(new FileReport());
        when(cajaMapperApi.fileDomainToResponse(any())).thenReturn(new FileReportResponse());
        ResponseEntity<FileReportResponse> responseEntity = cajaController.findByDateSemanal(from, to);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for the findByDateMensual controller method")
    void findByDateMensual() throws CajaNotFoundException, IOException, JRException {
        LocalDate date = LocalDate.of(2023, 10, 1);
        when(getByDateMensualUseCase.findByDateMensual(date))
                .thenReturn(new FileReport());
        when(cajaMapperApi.fileDomainToResponse(any())).thenReturn(new FileReportResponse());
        ResponseEntity<FileReportResponse> responseEntity = cajaController.findByDateMensual(date);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for the findByIdSeller controller method")
    void findByIdSeller() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        Long idSeller = 123L;
        List<CajaResponse> cajaResponses = List.of(new CajaResponse());
        when(getCajasByIdSellerUseCase.findCajasByIdSeller(idSeller)).thenReturn(List.of(new Caja()));
        when(cajaMapperApi.domainsToResponses(any())).thenReturn(cajaResponses);
        ResponseEntity<List<CajaResponse>> responseEntity = cajaController.findByIdSeller(idSeller);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(cajaResponses, responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for findCajasByMatchesAndStatus method")
    void findCajasByMatchesAndStatus_ValidFilterAndStatus_ReturnCajas() {
        String filterText = "test";
        Estado status = Estado.ACTIVO;
        CajaMatchResponse cajaMatchResponse = new CajaMatchResponse();
        Caja caja = new Caja();
        when(getAllByMatchAndStatusUseCase.findAllByMatchesAndStatus(filterText, status)).thenReturn(List.of(caja));
        when(cajaMapperApi.domainsToMatchResponses(List.of(caja))).thenReturn(List.of(cajaMatchResponse));
        ResponseEntity<List<CajaMatchResponse>> response = cajaController.findByMatchesAndStatus(filterText,status);
        Assertions.assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    @DisplayName("Test for the findCajaByIdSeller controller method")
    void findCajaByIdSeller() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        Long idSeller = 123L;
        CajaResponse cajaResponse = new CajaResponse();
        when(getCajaByUserIdUseCase.findCajaByUserId(idSeller)).thenReturn(new Caja());
        when(cajaMapperApi.domainToResponse(any())).thenReturn(cajaResponse);
        ResponseEntity<CajaResponse> responseEntity = cajaController.findCajaByIdSeller(idSeller);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(cajaResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for the findCajaById controller method")
    void findCajaById() {
        BigInteger cajaId = BigInteger.valueOf(123);
        CajaResponse cajaResponse = new CajaResponse();
        when(getCajaByIdUseCase.findById(cajaId)).thenReturn(domain);
        when(cajaMapperApi.domainToResponse(any())).thenReturn(cajaResponse);
        ResponseEntity<CajaResponse> responseEntity = cajaController.findCajaById(cajaId);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(cajaResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for the findCajaByMac controller method")
    void findCajaByMac() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        String mac = "sampleMac";
        Long companyId = 123L;
        CajaInfoResponse cajaInfoResponse = new CajaInfoResponse();
        cajaInfoResponse.setId(BigInteger.ONE);
        cajaInfoResponse.setNombreCaja("caja");
        cajaInfoResponse.setCodigoCaja("123");
        cajaInfoResponse.setSerial("123");
        CajaInfo cajaInfo = new CajaInfo();
        cajaInfo.setId(BigInteger.ONE);
        cajaInfo.setNombreCaja("caja");
        cajaInfo.setCodigoCaja("123");
        cajaInfo.setSerial("123");
        when(getCajaByMacUseCase.findByMac(mac, companyId)).thenReturn(cajaInfo);
        when(cajaMapperApi.domainToCajaInfoResponse(any())).thenReturn(cajaInfoResponse);
        ResponseEntity<CajaInfoResponse> responseEntity = cajaController.findCajaByMac(mac, companyId);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(cajaInfoResponse, responseEntity.getBody());
    }
}
