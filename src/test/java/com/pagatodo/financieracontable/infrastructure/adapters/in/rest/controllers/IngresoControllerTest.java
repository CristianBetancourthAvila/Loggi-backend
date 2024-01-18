package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.vouchers.IngresoVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.IngresoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.IngresoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.IngresoRqVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.ingreso.IngresoMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.ingreso.IngresoUseCase;
import net.sf.jasperreports.engine.JRException;
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

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class IngresoControllerTest {

    @Mock
    private IngresoUseCase ingresoUseCase;

    @Mock
    private IngresoMapperApi ingresoMapperApi;

    private static String cancellationReason;

    private static Ingreso domain;

    private static IngresoRequest request;

    private static IngresoResponse response;

    private static IngresoRqVoucher rqVoucher;

    private static IngresoVoucher voucher;

    @BeforeAll
    static void configInitial() {
        cancellationReason = "Motivo de anulacion";

        domain = new Ingreso();
        domain.setId(1);
        domain.setHoraCreacion(LocalTime.now());
        domain.setFechaCreacion(LocalDate.of(2023, 10, 22));
        domain.setAperturaCaja(new AperturaCaja());
        domain.setParametrizacionConcepto(new ParametrizacionConcepto());
        domain.setMedioPagoId(4);
        domain.setUsuarioTerceroId(8);
        domain.setValorRecibido(25000L);
        domain.setMotivoAnulacion("Motivo anulacion");
        domain.setObservaciones("Observaciones prueba");

        request = new IngresoRequest();
        request.setId(4);
        request.setAperturaCajaId(1L);
        request.setParametrizacionConceptoId(4L);
        request.setMedioPagoId(5);
        request.setUsuarioTerceroId(4);
        request.setMotivoAnulacion("Motivo anulacion");
        request.setValorRecibido(25000L);
        request.setObservaciones("Pago extra");

        response = new IngresoResponse();
        response.setId(1);
        response.setFechaCreacion(LocalDate.now());
        response.setHoraCreacion(LocalTime.now());
        response.setUsuarioTerceroId(4);

        voucher = new IngresoVoucher();
        voucher.setConcepto("255655-Transacción");
        voucher.setComprobante(524L);
        voucher.setDetalle("14125-Recaudo");
        voucher.setRecibido("LEONEL ANDRES HIGUITA");
        voucher.setObservacion("Una observación");
        voucher.setValor("100.000.000");
        voucher.setFechaHora("25/08/2024 10:22:00 AM");
        voucher.setTipoDocumento("CC");
        voucher.setNumeroDocumento("115689822");

        rqVoucher = new IngresoRqVoucher();
        rqVoucher.setConcepto("255655-Transacción");
        rqVoucher.setComprobante(524L);
        rqVoucher.setDetalle("14125-Recaudo");
        rqVoucher.setRecibido("LEONEL ANDRES HIGUITA");
        rqVoucher.setObservacion("Una observación");
        rqVoucher.setValor("100.000.000");
        rqVoucher.setFechaHora("25/08/2024 10:22:00 AM");
        rqVoucher.setTipoDocumento("CC");
        rqVoucher.setNumeroDocumento("115689822");

    }

    @Test
    @DisplayName("Test for create a record of ingreso")
    void save_Ingreso() throws Exception {

        IngresoController ingresoController = new IngresoController(ingresoUseCase, ingresoMapperApi);

        Mockito
                .when(ingresoUseCase.create(Mockito.any(Ingreso.class)))
                .thenReturn(domain);

        Mockito
                .when(ingresoMapperApi.requestToDomain(Mockito.any(IngresoRequest.class)))
                .thenReturn(domain);

        Mockito
                .when(ingresoMapperApi.domainToResponse(Mockito.any(Ingreso.class)))
                .thenReturn(response);

        final ResponseEntity<IngresoResponse> response = ingresoController.save(request);

        assertAll("resultado",
                () -> assertNotNull(response));

    }

    @Test
    @DisplayName("Test for updating a field called motivoAnulacion of ingreso")
    void update_cancellation_reason() throws Exception {

        IngresoController ingresoController = new IngresoController(ingresoUseCase, ingresoMapperApi);

        doNothing().when(ingresoUseCase).updateCancellationReason(Mockito.any(Integer.class), Mockito.anyString());

        final ResponseEntity<Void> message = ingresoController.updateCancellationReason(domain.getId(), cancellationReason);

        assertAll("resultado",
                () -> assertNotNull(message),
                () -> assertEquals(HttpStatus.OK, message.getStatusCode()));

    }

    @Test
    @DisplayName("Test for the generateVoucher controller method")
    void generateVoucher_ingreso() throws JRException {

        IngresoController ingresoController = new IngresoController(ingresoUseCase, ingresoMapperApi);

        FileReportResponse response = new FileReportResponse();
        response.setFile("base64");

        Mockito
                .when(ingresoMapperApi.requestVoucherToVoucher(Mockito.any(IngresoRqVoucher.class)))
                .thenReturn(voucher);

        Mockito
                .when(ingresoUseCase.generateVoucher(Mockito.any(IngresoVoucher.class)))
                .thenReturn(new FileReport());

        Mockito
                .when(ingresoMapperApi.domainToResponse(Mockito.any(FileReport.class)))
                .thenReturn(response);

        final ResponseEntity<FileReportResponse> result = ingresoController.generateVoucher(rqVoucher);

        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
}
