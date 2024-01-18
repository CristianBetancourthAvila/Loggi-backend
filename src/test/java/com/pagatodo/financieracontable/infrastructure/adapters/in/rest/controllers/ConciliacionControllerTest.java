package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.ConciliacionRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ConciliacionRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConciliacionFilterResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConciliacionResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.conciliacion.ConciliacionMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.conciliacion.CreateConciliacionUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.in.conciliacion.GetConciliacionByCriteriaUseCase;
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
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConciliacionControllerTest {

    @InjectMocks
    private ConciliacionController conciliacionController;

    @Mock
    private CreateConciliacionUseCase createConciliacionUseCase;

    @Mock
    private GetConciliacionByCriteriaUseCase getConciliacionByCriteriaUseCase;

    @Mock
    private ConciliacionMapperApi conciliacionMapperApi;

    private ConciliacionRequest conciliacionRequest;
    private ConciliacionResponse conciliacionResponse;
    private Conciliacion conciliacion;
    private ConciliacionRqFilter conciliacionRqFilter;
    private ConciliacionFilter conciliacionFilter;

    private ConciliacionFilterResponse conciliacionFilterResponse;

    @BeforeEach
    void setUp() {
        conciliacionRequest = new ConciliacionRequest();
        conciliacionResponse = new ConciliacionResponse();
        conciliacion = new Conciliacion();

        conciliacionRqFilter = new ConciliacionRqFilter();
        conciliacionRqFilter.setTipoConciliacion(TipoConciliacion.BANCARIA);
        conciliacionRqFilter.setAliadoProducto("ALIADO_001");
        conciliacionRqFilter.setFechaInicial(LocalDate.of(2023, 1, 1));
        conciliacionRqFilter.setFechaFinal(LocalDate.of(2023, 12, 31));

        conciliacionFilter = new ConciliacionFilter();
        conciliacionFilter.setTipoConciliacion(TipoConciliacion.BANCARIA);
        conciliacionFilter.setAliadoProducto("ALIADO_001");
        conciliacionFilter.setFechaInicial(LocalDate.of(2023, 1, 1));
        conciliacionFilter.setFechaFinal(LocalDate.of(2023, 12, 31));

        conciliacionFilterResponse = new ConciliacionFilterResponse();
        conciliacionFilter.setTipoConciliacion(TipoConciliacion.BANCARIA);
        conciliacionFilter.setAliadoProducto("ALIADO_001");
        conciliacionFilter.setFechaInicial(LocalDate.of(2023, 1, 1));
        conciliacionFilter.setFechaFinal(LocalDate.of(2023, 12, 31));
    }

    @Test
    @DisplayName("Test for the create controller method")
    void create_Conciliacion() throws ConciliacionBusinessException {
        when(createConciliacionUseCase.create(conciliacion))
                .thenReturn(conciliacion);
        when(conciliacionMapperApi.requestToDomain(conciliacionRequest))
                .thenReturn(conciliacion);
        when(conciliacionMapperApi.domainToResponse(conciliacion))
                .thenReturn(conciliacionResponse);
        ResponseEntity<ConciliacionResponse> responseEntity = conciliacionController.create(conciliacionRequest);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(conciliacionResponse, responseEntity.getBody());
    }

    @Test
    @DisplayName("Test for findWithFilter controller method")
    void findWithFilter_Conciliacion() throws ConciliacionBusinessException {
        int rowsPerPage = 10;
        int skip = 0;

        List<Conciliacion> conciliacionList = Arrays.asList(
                new Conciliacion(),
                new Conciliacion()
        );

        PageModel<List<Conciliacion>> expectedResult = new PageModel<>(conciliacionList,BigInteger.valueOf(conciliacionList.size()));

        when(getConciliacionByCriteriaUseCase.findWithFiler(conciliacionFilter, rowsPerPage, skip))
                .thenReturn(expectedResult);
        when(conciliacionMapperApi.domainsToFilterResponse(expectedResult.data())).thenReturn(List.of(conciliacionFilterResponse));
        when(conciliacionMapperApi.requestFilterToDomain(conciliacionRqFilter)).thenReturn(conciliacionFilter);

        ResponseEntity<PageResponse<List<ConciliacionFilterResponse>>> responseEntity =
                conciliacionController.findWithFilter(conciliacionRqFilter, rowsPerPage, skip);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}
