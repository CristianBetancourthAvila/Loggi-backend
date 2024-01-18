package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers;

import com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioBusinessException;
import com.pagatodo.financieracontable.domain.models.LibroDiario;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.LibroDiarioRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.LibroDiarioResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.librodiario.LibroDiarioMapperApi;
import com.pagatodo.financieracontable.infrastructure.ports.in.librodiario.GetLibroDiarioByCriteriaUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LibroDiarioControllerTest {

    @InjectMocks
    private LibroDiarioController libroDiarioController;

    @Mock
    private LibroDiarioMapperApi libroDiarioMapperApi;

    @Mock
    private GetLibroDiarioByCriteriaUseCase getLibroDiarioByCriteriaUseCase;


    @Test
    @DisplayName("Test for findWithFilter method")
    void findWithFilterTest() throws LibroDiarioBusinessException {
        LibroDiarioRqFilter libroDiarioRqFilter = new LibroDiarioRqFilter();
        Integer rowsPerPage = 10;
        Integer skip = 0;
        LibroDiario libroDiario = new LibroDiario();
        LibroDiarioFilter libroDiarioFilter = Mockito.mock(LibroDiarioFilter.class);
        Mockito.when(libroDiarioMapperApi.requestToDomain(libroDiarioRqFilter)).thenReturn(libroDiarioFilter);
        Mockito.when(getLibroDiarioByCriteriaUseCase.findWithFiler(libroDiarioFilter, rowsPerPage, skip))
                .thenReturn(new PageModel<>(List.of(libroDiario), BigInteger.ZERO));
        ResponseEntity<PageResponse<List<LibroDiarioResponse>>> responseEntity =
                libroDiarioController.findWithFilter(libroDiarioRqFilter, rowsPerPage, skip);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}
