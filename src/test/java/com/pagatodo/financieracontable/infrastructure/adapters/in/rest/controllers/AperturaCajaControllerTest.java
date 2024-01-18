package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.AperturaCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.AperturaCajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.aperturacaja.AperturaCajaMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.aperturacaja.AperturaCajaUseCase;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class AperturaCajaControllerTest {

    @Mock
    private AperturaCajaUseCase aperturaCajaUseCase;

    @Mock
    private AperturaCajaMapperApi aperturaCajaMapperApi;

    private static AperturaCaja domain;

    private static AperturaCajaResponse response;

    private static AperturaCajaRequest request;

    private static Caja caja;

    private static Long id = 1L;

    @BeforeAll
    static void configInitial() {

        BigInteger cajaId = BigInteger.valueOf(1);
        caja = new Caja();
        caja.setId(cajaId);
        caja.setNombreCaja("BAZAR ALSACIA3");
        caja.setCodigoCaja("CJ00002");

        domain = new AperturaCaja();
        domain.setId(id);
        domain.setUsuarioId(BigInteger.valueOf(4));
        domain.setCaja(caja);
        domain.setEstado(true);
        domain.setSaldoInicial(50000L);
        domain.setBilletes(30000L);
        domain.setMonedas(20000L);

        response = new AperturaCajaResponse();
        response.setNombreCaja(caja.getNombreCaja());
        response.setCodigoCaja(caja.getCodigoCaja());
        response.setAbierta(true);

        request = new AperturaCajaRequest();
        request.setId(BigInteger.valueOf(1));
        request.setUsuarioId(BigInteger.valueOf(1));
        request.setCajaId(BigInteger.valueOf(7));
        request.setSaldoInicial(50000L);
        request.setBilletes(30000L);
        request.setMonedas(10000L);
        request.setPremios(5000L);
        request.setOtros(5000L);
        request.setObservaciones("Pago extra");

    }

    @Test
    @DisplayName("Test for create a record of AperturaCaja")
    void save_AperturaCaja() throws Exception {

        Mockito.when(aperturaCajaUseCase.create(Mockito.any(AperturaCaja.class)))
                .thenReturn(domain);

        Mockito.when(aperturaCajaMapperApi.requestToDomain(Mockito.any(AperturaCajaRequest.class)))
                .thenReturn(domain);

        AperturaCajaController controller = new AperturaCajaController(aperturaCajaUseCase, aperturaCajaMapperApi);

        final ResponseEntity<?> response = controller.save(request);

        assertAll("resultado",
                () -> assertNotNull(response));
    }

    @Test
    @DisplayName("Test to validate if a caja is already open")
    void validate_AperturaCaja() throws Exception {

        Mockito.when(aperturaCajaUseCase.validateStatus(Mockito.any(BigInteger.class)))
                .thenReturn(true);

        Mockito.when(aperturaCajaUseCase.findAssignedCajaByCajaId(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        Mockito.when(aperturaCajaUseCase.findLastRecord(Mockito.any(BigInteger.class)))
                .thenReturn(domain);

        Mockito.when(aperturaCajaMapperApi.domainToResponse(Mockito.any(Caja.class), Mockito.any(Boolean.class), Mockito.any(AperturaCaja.class), Mockito.anyBoolean(), Mockito.anyLong()))
                .thenReturn(response);

        AperturaCajaController controller = new AperturaCajaController(aperturaCajaUseCase, aperturaCajaMapperApi);

        final ResponseEntity<AperturaCajaResponse> currentResponse = controller.validate(BigInteger.ONE, true);

        assertAll("resultado",
                () -> assertNotNull(currentResponse));
    }

    @Test
    @DisplayName("Test to update the status of an apertura caja with id")
    void updateStatus_AperturaCaja() throws Exception {

        AperturaCajaController controller = new AperturaCajaController(aperturaCajaUseCase, aperturaCajaMapperApi);

        doNothing().when(aperturaCajaUseCase).updateStatus(Mockito.any(Long.class));

        final ResponseEntity<Void> message = controller.updateStatus(domain.getId());

        assertAll("resultado",
                () -> assertNotNull(message),
                () -> assertEquals(HttpStatus.OK, message.getStatusCode()));

    }
}
