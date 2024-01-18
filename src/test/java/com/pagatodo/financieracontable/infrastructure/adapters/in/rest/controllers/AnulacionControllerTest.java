package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.domain.models.filter.AnulacionFilter;
import com.pagatodo.financieracontable.domain.models.vouchers.AnulacionVoucher;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.AnulacionRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.AnulacionResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.AnulacionRqVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.anulacion.AnulacionMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.anulacion.AnulacionUseCase;
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

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AnulacionControllerTest {

    @Mock
    private AnulacionUseCase anulacionUseCase;

    @Mock
    private AnulacionMapperApi anulacionMapperApi;

    private static AnulacionResponse response;
    private static Anulacion domain;

    private static AnulacionRqVoucher rqVoucher;

    private static AnulacionVoucher voucher;

    @BeforeAll
    static void configInitial() {
        domain = new Anulacion();
        domain.setId(1);
        domain.setHoraCreacion(LocalTime.now());
        domain.setIngreso(new Ingreso());
        domain.setEstado(EstadoAnulacion.PENDIENTE);
        domain.setAutorizadorId(null);
        domain.setTipoMovimiento(TipoMovimiento.INGRESO);

        response = new AnulacionResponse();
        response.setId(1);
        response.setMotivo("MOTIVO");
        response.setValor(20000L);
        response.setCodigoConcepto(1L);
        response.setAutorizado(1);
        response.setIdentificacion(4L);
        response.setFechaSolicitud(LocalDate.now());
        response.setHoraSolicitud(LocalTime.now());
        response.setEstadoAnulacion(EstadoAnulacion.PENDIENTE);

        voucher = new AnulacionVoucher();
        voucher.setConcepto("255655-Transacción");
        voucher.setComprobante(524L);
        voucher.setMotivo("Motivo");
        voucher.setRecibido("LEONEL ANDRES HIGUITA");
        voucher.setAutorizador("Jhoan Sebastian");
        voucher.setIdentificacion("156756566");
        voucher.setFechaSolicitud("11/12/2023");
        voucher.setAutorizado("Duplicado");
        voucher.setComprobanteMovimiento("4251");
        voucher.setValor("100.000.000");
        voucher.setFechaHora("25/08/2024 10:22:00 AM");
        voucher.setTipoDocumento("CC");
        voucher.setNumeroDocumento("115689822");

        rqVoucher = new AnulacionRqVoucher();
        rqVoucher.setConcepto("255655-Transacción");
        rqVoucher.setComprobante(524L);
        rqVoucher.setMotivo("Motivo");
        rqVoucher.setRecibido("LEONEL ANDRES HIGUITA");
        rqVoucher.setAutorizador("Jhoan Sebastian");
        rqVoucher.setIdentificacion("156756566");
        rqVoucher.setFechaSolicitud("11/12/2023");
        rqVoucher.setAutorizado("Duplicado");
        rqVoucher.setComprobanteMovimiento("4251");
        rqVoucher.setValor("100.000.000");
        rqVoucher.setFechaHora("25/08/2024 10:22:00 AM");
        rqVoucher.setTipoDocumento("CC");
        rqVoucher.setNumeroDocumento("115689822");

    }

    @Test
    @DisplayName("Test for the findAnulacionesByCriteria controller method")
    void findAnulacionesByCriteria(){
        AnulacionController controller = new AnulacionController(anulacionUseCase, anulacionMapperApi);
        AnulacionFilter anulacionFilter = new AnulacionFilter();
        Integer rowsPerPage = 10;
        Integer skip = 0;
        PageModel<List<Anulacion>> paginatedResult = new PageModel<>(List.of(domain), BigInteger.ONE);
        Mockito.when(anulacionUseCase.findAnulacionesByCriteria(anulacionFilter, rowsPerPage, skip))
                .thenReturn(paginatedResult);
        List<AnulacionResponse> anulacionResponses = List.of(response);
        Mockito.when(anulacionMapperApi.domainsToResponses(any()))
                .thenReturn(anulacionResponses);
        Mockito.when(anulacionMapperApi.requestFilterToDomain(any()))
                .thenReturn(anulacionFilter);
        ResponseEntity<PageResponse<List<AnulacionResponse>>> responseEntity = controller.findAnulacionesByCriteria(new AnulacionRqFilter(), rowsPerPage, skip);
        assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        PageResponse<List<AnulacionResponse>> resultPage = responseEntity.getBody();
        Assertions.assertEquals(anulacionResponses, resultPage.getData());
        Assertions.assertEquals(BigInteger.ONE, resultPage.getTotal());
    }

    @Test
    @DisplayName("Test for the updateAuthorizerUser controller method")
    void updateAuthorizerUser() throws NotFoundException {
        AnulacionController controller = new AnulacionController(anulacionUseCase, anulacionMapperApi);

        Mockito
                .doNothing()
                .when(anulacionUseCase)
                .updateAuthorizerUser(Mockito.any(Integer.class), Mockito.any(Integer.class));

        final ResponseEntity<Void> message = controller.updateAuthorizerUser(domain.getId(), 1);

        assertAll("resultado",
                () -> assertNotNull(message),
                () -> assertEquals(HttpStatus.OK, message.getStatusCode()));

    }

    @Test
    @DisplayName("Test for the generateVoucher controller method")
    void generateVoucher_anulacion() throws JRException {

        AnulacionController controller = new AnulacionController(anulacionUseCase, anulacionMapperApi);

        FileReportResponse response = new FileReportResponse();
        response.setFile("base64");

        Mockito
                .when(anulacionMapperApi.requestVoucherToVoucher(Mockito.any(AnulacionRqVoucher.class)))
                .thenReturn(voucher);

        Mockito
                .when(anulacionUseCase.generateVoucher(Mockito.any(AnulacionVoucher.class)))
                .thenReturn(new FileReport());

        Mockito
                .when(anulacionMapperApi.domainToResponse(Mockito.any(FileReport.class)))
                .thenReturn(response);

        final ResponseEntity<FileReportResponse> result = controller.generateVoucher(rqVoucher);

        assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

}
