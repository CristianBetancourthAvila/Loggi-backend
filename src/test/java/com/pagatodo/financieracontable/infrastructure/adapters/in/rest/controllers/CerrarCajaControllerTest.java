package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.CerrarCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CerrarCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.cerrarcaja.CerrarCajaMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.cerrarcaja.CerrarCajaUseCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CerrarCajaControllerTest {

    @Mock
    private CerrarCajaUseCase cerrarCajaUseCase;

    @Mock
    private CerrarCajaMapperApi cerrarCajaMapperApi;

    private CerrarCajaController cerrarCajaController;

    private static CerrarCaja domain;

    private static CerrarCajaRequest request;

    private static Long id = 1L;

    @BeforeAll
    static void configInitial() {
        AperturaCaja aperturaCaja = new AperturaCaja();
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        aperturaCaja.setId(id);
        aperturaCaja.setCaja(caja);

        domain = new CerrarCaja();
        domain.setId(id);
        domain.setAperturaCaja(aperturaCaja);
        domain.setNumeroBolsa("123");
        domain.setMonedas(1000L);
        domain.setBilletes(1000L);
        domain.setHoraCierre(LocalTime.now());
        domain.setFechaCierre(LocalDate.now());
        domain.setSaldoFinal(2000L);

        request = new CerrarCajaRequest();
        request.setAperturaCajaId(1L);
        request.setObservaciones("Observacion");
        request.setBilletes(10000L);
        request.setMonedas(5000L);
        request.setSaldoFinal(15000L);
        request.setNumeroBolsa("1255");
    }

    @Test
    @DisplayName("Test for create a record of CerrarCaja")
    void save_CerrarCaja() throws Exception {

        cerrarCajaController = new CerrarCajaController(cerrarCajaUseCase, cerrarCajaMapperApi);

        Mockito
                .when(cerrarCajaUseCase.create(Mockito.any(CerrarCaja.class)))
                .thenReturn(domain);

        Mockito
                .when(cerrarCajaMapperApi.requestToDomain(Mockito.any(CerrarCajaRequest.class)))
                .thenReturn(domain);

        final ResponseEntity<?> response = cerrarCajaController.save(request);

        assertAll("resultado",
                () -> assertNotNull(response));
    }
}
