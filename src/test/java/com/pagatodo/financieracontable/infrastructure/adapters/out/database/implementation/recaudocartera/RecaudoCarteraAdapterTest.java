package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.recaudocartera;


import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.domain.models.RecaudoCartera;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AperturaCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CarteraEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.RecaudoCarteraEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.recaudocartera.RecaudoCarteraMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.RecaudoCarteraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RecaudoCarteraAdapterTest {

    @Mock
    private RecaudoCarteraMapper recaudoCarteraMapper;

    @Mock
    private RecaudoCarteraRepository recaudoCarteraRepository;

    @InjectMocks
    private RecaudoCarteraAdapter recaudoCarteraPort;

    private static Integer id = 1;

    private static RecaudoCartera domain;

    private static RecaudoCarteraEntity entity;

    @BeforeEach
    void setUp() {
        recaudoCarteraPort = new RecaudoCarteraAdapter(recaudoCarteraRepository, recaudoCarteraMapper);

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

        entity = new RecaudoCarteraEntity();
        entity.setId(id);
        entity.setCartera(new CarteraEntity());
        entity.setAperturaCaja(new AperturaCajaEntity());
        entity.setObservaciones("Observaciones");
        entity.setValorRecaudo(1000L);
        entity.setMedioPagoId(4);
        entity.setFechaCreacion(LocalDate.now());
        entity.setHoraCreacion(LocalTime.now());
    }

    @Test
    @DisplayName("Test for creating a record of recaudo cartera")
    void create_createRecaudoCartera_success() {
        Mockito
                .when(recaudoCarteraMapper.domainToEntity(Mockito.any(RecaudoCartera.class)))
                .thenReturn(entity);

        Mockito
                .when(recaudoCarteraRepository.save(Mockito.any(RecaudoCarteraEntity.class)))
                .thenReturn(entity);

        Mockito
                .when(recaudoCarteraMapper.entityToDomain(Mockito.any(RecaudoCarteraEntity.class)))
                .thenReturn(domain);

        var result = recaudoCarteraPort.save(domain);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(id, result.getId()));
    }
}
