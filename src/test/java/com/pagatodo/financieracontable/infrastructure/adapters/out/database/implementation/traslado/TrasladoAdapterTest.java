package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.traslado;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.TrasladoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.traslado.TrasladoMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.TrasladoRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.traslado.TrasladoPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class TrasladoAdapterTest {

    @Mock
    private TrasladoMapper trasladoMapper;

    @Mock
    private TrasladoRepository trasladoRepository;

    private TrasladoPort trasladoPort;

    private static Integer id = 1;

    private static Traslado domain;

    private static List<Traslado> domainList;

    private static TrasladoEntity entity;

    @BeforeAll
    static void configInitial() {
        domain = new Traslado();
        domain.setId(id);
        domain.setFechaCreacion(LocalDate.now());
        domain.setEstado(EstadoTraslado.EN_PROCESO);
        domain.setTrasladarPremio(false);
        domain.setCajaOrigen(new Caja());
        domain.setCajaDestino(new Caja());
        domain.setHoraCreacion(LocalTime.now());

        entity = new TrasladoEntity();
        entity.setId(id);
        entity.setHoraCreacion(LocalTime.now());
        entity.setFechaCreacion(LocalDate.now());
        entity.setEstado(EstadoTraslado.PROGRAMADO);
        entity.setTrasladarPremio(false);
        entity.setCajaOrigen(new CajaEntity());
        entity.setCajaDestino(new CajaEntity());

        domainList = new ArrayList<>();
        domainList.add(new Traslado());
        domainList.add(new Traslado());
        domainList.add(new Traslado());
    }

    @Test
    @DisplayName("Test for creating a record of traslado")
    void create_createTraslado_success(){

        trasladoPort = new TrasladoAdapter(trasladoMapper, trasladoRepository);

        Mockito
                .when(trasladoMapper.domainToEntity(Mockito.any(Traslado.class)))
                .thenReturn(entity);

        Mockito
                .when(trasladoRepository.save(Mockito.any(TrasladoEntity.class)))
                .thenReturn(entity);

        Mockito
                .when(trasladoMapper.entityToDomain(Mockito.any(TrasladoEntity.class)))
                .thenReturn(domain);

        var result = trasladoPort.create(domain);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(id, result.getId()));


    }

    @Test
    @DisplayName("Test for finding traslados by date")
    void findTrasladosByDate_success(){

        trasladoPort = new TrasladoAdapter(trasladoMapper, trasladoRepository);

        Page<TrasladoEntity> page = new PageImpl<>(Collections.singletonList(entity), PageRequest.of(0, 1),1);

        Mockito
                .when(trasladoRepository.findTrasladosByDate(Mockito.any(LocalDate.class), Mockito.any(Pageable.class)))
                .thenReturn(page);

        Mockito
                .when(trasladoMapper.entitiesToDomains(Mockito.anyList()))
                .thenReturn(domainList);

        var result = trasladoPort.findTrasladosByDate(LocalDate.now(), 1, 2);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.total().longValue()));
    }

    @Test
    @DisplayName("Test for finding traslados to send or receive by caja")
    void findSendReceiveTrasladosByCaja_success(){

        trasladoPort = new TrasladoAdapter(trasladoMapper, trasladoRepository);

        Page<TrasladoEntity> page = new PageImpl<>(Collections.singletonList(entity), PageRequest.of(0, 1),1);

        Mockito
                .when(trasladoRepository.findSendReceiveTrasladosByCaja(Mockito.any(BigInteger.class), Mockito.any(Boolean.class), Mockito.any(Pageable.class)))
                .thenReturn(page);

        Mockito
                .when(trasladoMapper.entitiesToDomains(Mockito.anyList()))
                .thenReturn(domainList);

        var result = trasladoPort.findSendReceiveTrasladosByCaja(BigInteger.ONE, true, 1, 2);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.total().longValue()));
    }

}
