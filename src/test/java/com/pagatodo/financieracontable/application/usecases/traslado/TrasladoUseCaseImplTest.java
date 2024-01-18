package com.pagatodo.financieracontable.application.usecases.traslado;


import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.financieracontable.application.exceptions.traslado.TrasladoBusinessException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.out.traslado.TrasladoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TrasladoUseCaseImplTest {

    @Mock
    private TrasladoPort trasladoPort;

    private static Integer id = 1;

    private static Traslado domain;

    @BeforeEach
    public void configInitial() {
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
    @DisplayName("Test for create method success")
    void create_savedTraslado_Success() throws BusinessException {
        TrasladoUseCaseImpl useCase = new TrasladoUseCaseImpl(trasladoPort);

        Mockito
                .when(trasladoPort.create(Mockito.any(Traslado.class)))
                .thenReturn(domain);

        var result = useCase.create(domain);

        assertAll("Save New Register of traslado Success Test",
                () -> assertNotNull(result),
                () -> assertNull(result.getId()));

    }

    @Test
    @DisplayName("Test for create method with an error called serie premio required")
    void create_savedTraslado_Error_serie_premio_required(){
        TrasladoUseCaseImpl useCase = new TrasladoUseCaseImpl(trasladoPort);

        domain.setTrasladarPremio(true);

        assertThrows(TrasladoBusinessException.class, () -> useCase.create(domain));

    }

    @Test
    @DisplayName("Test for findTrasladosByDate method success")
    void findTrasladosByDate_Success() {
        TrasladoUseCaseImpl useCase = new TrasladoUseCaseImpl(trasladoPort);

        PageModel<List<Traslado>> page = new PageModel<>(List.of(domain), BigInteger.valueOf(1L));

        Mockito
                .when(trasladoPort.findTrasladosByDate(Mockito.any(LocalDate.class), Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(page);

        var result = useCase.findTrasladosByDate(LocalDate.now(), 1, 1);

        assertEquals(page, result);
    }

    @Test
    @DisplayName("Test for findSendReceiveTrasladosByCaja method success")
    void findSendReceiveTrasladosByCaja_Success() {
        TrasladoUseCaseImpl useCase = new TrasladoUseCaseImpl(trasladoPort);

        PageModel<List<Traslado>> page = new PageModel<>(List.of(domain), BigInteger.valueOf(1L));

        Mockito
                .when(trasladoPort.findSendReceiveTrasladosByCaja(Mockito.any(BigInteger.class),Mockito.any(Boolean.class), Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(page);

        var result = useCase.findSendReceiveTrasladosByCaja(BigInteger.ONE, true, 1, 1);

        assertEquals(page, result);
    }
}
