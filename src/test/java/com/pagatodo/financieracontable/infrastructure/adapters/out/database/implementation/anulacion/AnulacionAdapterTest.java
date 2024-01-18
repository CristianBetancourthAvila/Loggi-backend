package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.anulacion;

import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AnulacionEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.IngresoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.anulacion.AnulacionMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.AnulacionRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.anulacion.AnulacionPort;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AnulacionAdapterTest {

    @Mock
    private AnulacionMapper anulacionMapper;

    @Mock
    private AnulacionRepository anulacionRepository;

    private AnulacionPort anulacionPort;

    private static Integer id = 1;

    private static Anulacion domain;

    private static AnulacionEntity entity;

    private static List<AnulacionEntity> anulacionEntityList;

    private static List<Anulacion> anulacionList;

    @BeforeAll
    static void configInitial() {
        domain = new Anulacion();
        domain.setId(id);
        domain.setHoraCreacion(LocalTime.now());
        domain.setIngreso(new Ingreso());
        domain.setTipoMovimiento(TipoMovimiento.INGRESO);
        domain.setFechaCreacion(LocalDate.now());
        domain.setAutorizadorId(null);
        domain.setEstado(EstadoAnulacion.PENDIENTE);

        entity = new AnulacionEntity();
        entity.setId(id);
        entity.setHoraCreacion(LocalTime.now());
        entity.setIngreso(new IngresoEntity());
        entity.setTipoMovimiento(TipoMovimiento.INGRESO);
        entity.setFechaCreacion(LocalDate.now());
        entity.setAutorizadorId(null);
        entity.setEstado(EstadoAnulacion.PENDIENTE);

        anulacionEntityList = new ArrayList<>();
        anulacionEntityList.add(new AnulacionEntity());
        anulacionEntityList.add(new AnulacionEntity());
        anulacionEntityList.add(new AnulacionEntity());

        anulacionList = new ArrayList<>();
        anulacionList.add(new Anulacion());
        anulacionList.add(new Anulacion());
        anulacionList.add(new Anulacion());
    }

    @Test
    @DisplayName("Test for creating a record of anulacion ")
    void create_createAnulacion_success(){
        anulacionPort = new AnulacionAdapter(anulacionMapper, anulacionRepository);

        Mockito
                .when(anulacionMapper.domainToEntity(Mockito.any(Anulacion.class)))
                .thenReturn(entity);

        Mockito
                .when(anulacionRepository.save(Mockito.any(AnulacionEntity.class)))
                .thenReturn(entity);

        Mockito
                .when(anulacionMapper.entityToDomain(Mockito.any(AnulacionEntity.class)))
                .thenReturn(domain);

        var result = anulacionPort.create(domain);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(id, result.getId()));
    }

    @Test
    @DisplayName("Test for finding a list of anulaciones ")
    void findAnulacionesByCriteria_success(){
        anulacionPort = new AnulacionAdapter(anulacionMapper, anulacionRepository);

        Page<AnulacionEntity> page = new PageImpl<>(Collections.singletonList(entity), PageRequest.of(0, 1),1);

        Mockito
                .when(anulacionRepository.findAnulacionesByCriteria(Mockito.any(TipoMovimiento.class),Mockito.any(LocalDate.class),Mockito.any(EstadoAnulacion.class), Mockito.any(Pageable.class)))
                .thenReturn(page);

        Mockito
                .when(anulacionMapper.entitiesToDomains(Mockito.anyList()))
                .thenReturn(anulacionList);

        var result = anulacionPort.findAnulacionesByCriteria(TipoMovimiento.INGRESO, LocalDate.now(), EstadoAnulacion.ANULADO, 2, 2);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.total().longValue()));
    }

    @Test
    @DisplayName("Test for updating a authorizer user and change anulacion's status to anulado")
    void updateAuthorizerUser_success(){
        anulacionPort = new AnulacionAdapter(anulacionMapper, anulacionRepository);

        Mockito
                .doNothing()
                .when(anulacionRepository)
                .updateAuthorizerUser(Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(EstadoAnulacion.class));

        anulacionPort.updateAuthorizerUser(domain.getId(),1, EstadoAnulacion.ANULADO);

        Mockito.verify(anulacionRepository,Mockito.times(1)).updateAuthorizerUser(domain.getId(),1, EstadoAnulacion.ANULADO);
    }
}
