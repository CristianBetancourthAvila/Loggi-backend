package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CarteraRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CarteraUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CarteraResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.cartera.CarteraMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.cartera.CarteraUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CarteraControllerTest {

    @Mock
    private CarteraMapperApi carteraMapperApi;

    @Mock
    private CarteraUseCase carteraUseCase;

    @InjectMocks
    private CarteraController carteraController;

    private static Integer id = 1;

    private static Cartera domain;

    private static CarteraRequest request;

    private static CarteraResponse response;

    private static Integer sellerId = 4;

    private static CarteraUpdateRequest updateRequest;

    @BeforeEach
    void configInitial() {
        carteraController = new CarteraController(carteraMapperApi, carteraUseCase);

        domain = new Cartera();
        domain.setSaldo(20000L);
        domain.setVendedorId(sellerId);
        domain.setId(id);
        domain.setVentaDiaLiquidada(20000L);
        domain.setVentasDia(10000L);
        domain.setHoraCreacion(LocalTime.now());
        domain.setFechaCreacion(LocalDate.now());

        request = new CarteraRequest();
        request.setVendedorId(sellerId);
        request.setId(id);
        request.setSaldo(500000L);

        response = new CarteraResponse();
        response.setSaldo(20000L);
        response.setVendedorId(sellerId);
        response.setId(id);
        response.setVentaDiaLiquidada(20000L);
        response.setVentasDia(10000L);
        response.setHoraCreacion(LocalTime.now());
        response.setFechaCreacion(LocalDate.now());

        updateRequest = new CarteraUpdateRequest();
        updateRequest.setId(id);
        updateRequest.setDiferenciaExcedenteVentasDia(10000L);
        updateRequest.setDiferenciaExcedenteSaldo(20000000L);
        updateRequest.setDiferenciaExcedenteVentaDiaLiquidada(5250000L);
    }

    @Test
    @DisplayName("Test for the create controller method")
    void save_cartera() throws BadRequestException, NotFoundException, UnknownException, ConnectionException {

        Mockito
                .when(carteraMapperApi.requestToDomain(Mockito.any(CarteraRequest.class)))
                .thenReturn(domain);

        Mockito
                .when(carteraUseCase.create(Mockito.any(Cartera.class)))
                .thenReturn(domain);

        Mockito
                .when(carteraMapperApi.domainToResponse(Mockito.any(Cartera.class)))
                .thenReturn(response);

        final ResponseEntity<CarteraResponse> response = carteraController.save(request);

        assertAll("resultado",
                () -> assertNotNull(response));

    }

    @Test
    @DisplayName("Test for the findCarteraByVendedorId controller method")
    void findCarteraByVendedorId_cartera() throws NotFoundException {

        Mockito
                .when(carteraUseCase.findCarteraByVendedorId(Mockito.any(Integer.class)))
                .thenReturn(domain);

        Mockito
                .when(carteraMapperApi.domainToResponse(Mockito.any(Cartera.class)))
                .thenReturn(response);

        final ResponseEntity<CarteraResponse> response = carteraController.findCarteraByVendedorId(sellerId);

        assertAll("resultado",
                () -> assertNotNull(response));

    }

    @Test
    @DisplayName("Test for the update controller method")
    void update_cartera() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {

        Mockito
                .when(carteraMapperApi.updateRequestToDomain(Mockito.any(CarteraUpdateRequest.class)))
                .thenReturn(domain);

        Mockito
                .when(carteraUseCase.update(Mockito.any(Cartera.class)))
                .thenReturn(domain);

        Mockito
                .when(carteraMapperApi.domainToResponse(Mockito.any(Cartera.class)))
                .thenReturn(response);

        final ResponseEntity<CarteraResponse> response = carteraController.update(updateRequest);

        assertAll("resultado",
                () -> assertNotNull(response));

    }
}
