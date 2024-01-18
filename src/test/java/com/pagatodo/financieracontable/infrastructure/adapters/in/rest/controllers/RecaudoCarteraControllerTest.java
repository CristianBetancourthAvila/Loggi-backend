package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;


import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.RecaudoCartera;
import com.pagatodo.financieracontable.domain.models.vouchers.RecaudoCarteraVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.RecaudoCarteraRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.RecaudoCarteraResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.RecaudoCarteraRqVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.recaudocartera.RecaudoCarteraMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.recaudocartera.RecaudoCarteraUseCase;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
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

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RecaudoCarteraControllerTest {

    @Mock
    private RecaudoCarteraMapperApi recaudoCarteraMapperApi;

    @Mock
    private RecaudoCarteraUseCase recaudoCarteraUseCase;

    @InjectMocks
    private RecaudoCarteraController recaudoCarteraController;

    private static Integer id = 1;

    private static RecaudoCartera domain;

    private static RecaudoCarteraRequest request;

    private static RecaudoCarteraResponse response;

    private static RecaudoCarteraRqVoucher rqVoucher;

    private static RecaudoCarteraVoucher voucher;

    @BeforeEach
    void configInitial() {
        recaudoCarteraController = new RecaudoCarteraController(recaudoCarteraMapperApi, recaudoCarteraUseCase);

        domain = new RecaudoCartera();
        domain.setId(id);
        Cartera cartera = new Cartera();
        domain.setCartera(cartera);
        AperturaCaja aperturaCaja = new AperturaCaja();
        domain.setAperturaCaja(aperturaCaja);
        domain.setObservaciones("Observaciones");
        domain.setValorRecaudo(1000L);
        domain.setMedioPagoId(4);
        domain.setFechaCreacion(LocalDate.now());
        domain.setHoraCreacion(LocalTime.now());

        request = new RecaudoCarteraRequest();
        request.setId(1);
        request.setCarteraId(1);
        request.setAperturaCajaId(1L);
        request.setObservaciones("Observaciones");
        request.setValorRecaudo(1000L);
        request.setMedioPagoId(4);

        response = new RecaudoCarteraResponse();
        response.setId(1);
        response.setCartera(cartera);
        response.setAperturaCaja(aperturaCaja);
        response.setObservaciones("Observaciones");
        response.setValorRecaudo(1000L);
        response.setMedioPagoId(4);
        response.setFechaCreacion(LocalDate.now());
        response.setHoraCreacion(LocalTime.now());

        rqVoucher = new RecaudoCarteraRqVoucher();
        rqVoucher.setCaja("CAJA125 - 1022");
        rqVoucher.setComprobante(524L);
        rqVoucher.setDetalle("14125-Recaudo");
        rqVoucher.setRecibido("LEONEL ANDRES HIGUITA");
        rqVoucher.setZona("Bolivar Centro Sur");
        rqVoucher.setValor("100.000.000");
        rqVoucher.setFechaHora("25/08/2024 10:22:00 AM");
        rqVoucher.setMedioPago("Efectivo");

        voucher = new RecaudoCarteraVoucher();
        voucher.setCaja("CAJA125 - 1022");
        voucher.setComprobante(524L);
        voucher.setDetalle("14125-Recaudo");
        voucher.setRecibido("LEONEL ANDRES HIGUITA");
        voucher.setZona("Bolivar Centro Sur");
        voucher.setValor("100.000.000");
        voucher.setFechaHora("25/08/2024 10:22:00 AM");
        voucher.setMedioPago("Efectivo");

    }

    @Test
    @DisplayName("Test for the create controller method")
    void save_recaudo_cartera() throws BadRequestException, NotFoundException, UnknownException, ConnectionException {
        Mockito
                .when(recaudoCarteraMapperApi.requestToDomain(Mockito.any(RecaudoCarteraRequest.class)))
                .thenReturn(domain);

        Mockito
                .when(recaudoCarteraUseCase.create(Mockito.any(RecaudoCartera.class)))
                .thenReturn(domain);

        Mockito
                .when(recaudoCarteraMapperApi.domainToResponse(Mockito.any(RecaudoCartera.class)))
                .thenReturn(response);

        final ResponseEntity<RecaudoCarteraResponse> response = recaudoCarteraController.save(request);

        assertAll("resultado",
                () -> assertNotNull(response));
    }

    @Test
    @DisplayName("Test for the generateVoucher controller method")
    void generateVoucher_cartera() throws JRException {

        FileReportResponse response = new FileReportResponse();
        response.setFile("base64");

        Mockito
                .when(recaudoCarteraMapperApi.requestVoucherToVoucher(Mockito.any(RecaudoCarteraRqVoucher.class)))
                .thenReturn(voucher);

        Mockito
                .when(recaudoCarteraUseCase.generateVoucher(Mockito.any(RecaudoCarteraVoucher.class)))
                .thenReturn(new FileReport());

        Mockito
                .when(recaudoCarteraMapperApi.domainToResponse(Mockito.any(FileReport.class)))
                .thenReturn(response);

        final ResponseEntity<FileReportResponse> result = recaudoCarteraController.generateVoucher(rqVoucher);

        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
}
