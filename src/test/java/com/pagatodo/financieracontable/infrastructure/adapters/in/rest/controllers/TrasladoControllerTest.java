package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.TrasladoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoFilterByDateResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoSendReceiveResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.traslado.TrasladoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.traslado.TrasladoUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TrasladoControllerTest {

    @Mock
    private TrasladoUseCase trasladoUseCase;

    @Mock
    private TrasladoMapperApi trasladoMapperApi;

    private static Integer id = 1;

    private static Traslado domain;

    private static TrasladoRequest request;

    private static TrasladoResponse response;

    private static TrasladoFilterByDateResponse responseFilterByDate;

    private static TrasladoSendReceiveResponse responseSendReceive;

    @BeforeAll
    static void configInitial() {
        request = new TrasladoRequest();
        request.setId(id);
        request.setObservacionTraslado("Observaciones");
        request.setCajaOrigenId(BigInteger.TWO);
        request.setCajaDestinoId(BigInteger.ONE);
        request.setSeriePremio(123);
        request.setTrasladarPremio(true);

        response = new TrasladoResponse();
        response.setId(1);
        response.setEstado(EstadoTraslado.PROGRAMADO);
        response.setObservacionTraslado("Observaciones");
        response.setCajaDestinoId(BigInteger.valueOf(100));
        response.setCajaOrigenId(BigInteger.valueOf(250));
        response.setTrasladarPremio(true);
        response.setSeriePremio(520);

        responseFilterByDate = new TrasladoFilterByDateResponse();
        responseFilterByDate.setNumeroBolsa("G1520");
        responseFilterByDate.setConsecutivo(1);
        responseFilterByDate.setValorDiferencia(1000);
        responseFilterByDate.setCajaOrigen("1002-CJ120");
        responseFilterByDate.setCajaDestino("1502-CJ520");
        responseFilterByDate.setEstado(EstadoTraslado.PROGRAMADO);

        responseSendReceive = new TrasladoSendReceiveResponse();
        responseSendReceive.setComprobante(1);
        responseSendReceive.setValor(5000L);
        responseSendReceive.setEstado(EstadoTraslado.PROGRAMADO);
        responseSendReceive.setConsecutivo(1);
        responseSendReceive.setCajaOrigenCajero("1001-CJ452");

        domain = new Traslado();
        domain.setId(id);
        domain.setHoraCreacion(LocalTime.now());
        domain.setFechaCreacion(LocalDate.now());
        domain.setEstado(EstadoTraslado.PROGRAMADO);
        domain.setTrasladarPremio(false);
        domain.setCajaOrigen(new Caja());
        domain.setCajaDestino(new Caja());
    }

    @Test
    @DisplayName("Test for create a record of traslado")
    void save_Traslado() throws BusinessException {

        TrasladoController controller = new TrasladoController(trasladoUseCase, trasladoMapperApi);

        Mockito
                .when(trasladoMapperApi.requestToDomain(Mockito.any(TrasladoRequest.class)))
                .thenReturn(domain);

        Mockito
                .when(trasladoUseCase.create(Mockito.any(Traslado.class)))
                .thenReturn(domain);

        Mockito
                .when(trasladoMapperApi.domainToResponse(Mockito.any(Traslado.class)))
                .thenReturn(response);

        final ResponseEntity<TrasladoResponse> response = controller.save(request);

        assertAll("resultado",
                () -> assertNotNull(response));

    }

    @Test
    @DisplayName("Test for the findTrasladosByDate controller method")
    void findTrasladosByDate(){
        TrasladoController controller = new TrasladoController(trasladoUseCase, trasladoMapperApi);

        Integer rowsPerPage = 10;
        Integer skip = 0;

        PageModel<List<Traslado>> paginatedResult = new PageModel<>(List.of(domain), BigInteger.ONE);

        Mockito
                .when(trasladoUseCase.findTrasladosByDate(Mockito.any(LocalDate.class), Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(paginatedResult);

        List<TrasladoFilterByDateResponse> trasladoResponses = List.of(responseFilterByDate);

        Mockito
                .when(trasladoMapperApi.domainsToFilterResponses(Mockito.anyList()))
                .thenReturn(trasladoResponses);

        ResponseEntity<PageResponse<List<TrasladoFilterByDateResponse>>> responseEntity = controller.findTrasladosByDate(LocalDate.now(), rowsPerPage, skip);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        PageResponse<List<TrasladoFilterByDateResponse>> resultPage = responseEntity.getBody();
        Assertions.assertEquals(trasladoResponses, resultPage.getData());
        Assertions.assertEquals(BigInteger.ONE, resultPage.getTotal());
    }

    @Test
    @DisplayName("Test for the findSendReceiveTrasladosByCaja controller method")
    void findSendReceiveTrasladosByCaja(){
        TrasladoController controller = new TrasladoController(trasladoUseCase, trasladoMapperApi);

        Integer rowsPerPage = 10;
        Integer skip = 0;

        PageModel<List<Traslado>> paginatedResult = new PageModel<>(List.of(domain), BigInteger.ONE);

        Mockito
                .when(trasladoUseCase.findSendReceiveTrasladosByCaja(Mockito.any(BigInteger.class), Mockito.any(Boolean.class), Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(paginatedResult);

        List<TrasladoSendReceiveResponse> trasladoResponses = List.of(responseSendReceive);

        Mockito
                .when(trasladoMapperApi.domainsToSendReceiveResponses(Mockito.anyList(), Mockito.anyBoolean()))
                .thenReturn(trasladoResponses);

        ResponseEntity<PageResponse<List<TrasladoSendReceiveResponse>>> responseEntity = controller.findSendReceiveTrasladosByCaja(BigInteger.ONE, true, rowsPerPage, skip);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        PageResponse<List<TrasladoSendReceiveResponse>> resultPage = responseEntity.getBody();
        Assertions.assertEquals(trasladoResponses, resultPage.getData());
        Assertions.assertEquals(BigInteger.ONE, resultPage.getTotal());
    }

}
