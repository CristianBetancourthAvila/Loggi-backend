package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.cartera;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraNotFoundByVendedorIdException;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraNotFoundException;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CarteraEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.cartera.CarteraMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.CarteraRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CarteraAdapterTest {

    @Mock
    private CarteraMapper carteraMapper;

    @Mock
    private CarteraRepository carteraRepository;

    @InjectMocks
    private CarteraAdapter carteraPort;

    private static Integer id = 1;

    private static Cartera domain;

    private static CarteraEntity entity;

    private static Integer sellerId = 4;

    @BeforeEach
    void setUp() {
        carteraPort = new CarteraAdapter(carteraMapper, carteraRepository);

        domain = new Cartera();
        domain.setSaldo(20000L);
        domain.setVendedorId(sellerId);
        domain.setId(id);
        domain.setVentaDiaLiquidada(20000L);
        domain.setVentasDia(10000L);
        domain.setHoraCreacion(LocalTime.now());
        domain.setFechaCreacion(LocalDate.now());

        entity = new CarteraEntity();
        entity.setSaldo(20000L);
        entity.setVendedorId(sellerId);
        entity.setId(id);
        entity.setVentaDiaLiquidada(20000L);
        entity.setVentasDia(10000L);
        entity.setHoraCreacion(LocalTime.now());
        entity.setFechaCreacion(LocalDate.now());
    }

    @Test
    @DisplayName("Test for creating a record of cartera")
    void create_createCartera_success() {
        Mockito
                .when(carteraMapper.domainToEntity(Mockito.any(Cartera.class)))
                .thenReturn(entity);

        Mockito
                .when(carteraRepository.save(Mockito.any(CarteraEntity.class)))
                .thenReturn(entity);

        Mockito
                .when(carteraMapper.entityToDomain(Mockito.any(CarteraEntity.class)))
                .thenReturn(domain);

        var result = carteraPort.save(domain);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(id, result.getId()));
    }

    @Test
    @DisplayName("Test for finding a record of cartera by seller id")
    void find_findCarteraByVendedorId_success(){

        Mockito
                .when(carteraRepository.findCarteraByVendedorId(Mockito.any(Integer.class)))
                .thenReturn(entity);

        Mockito
                .when(carteraMapper.entityToDomain(Mockito.any(CarteraEntity.class)))
                .thenReturn(domain);

        var result = carteraPort.findCarteraByVendedorId(sellerId);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(sellerId, result.getVendedorId()));
    }

    @Test
    @DisplayName("Test for updating a record of cartera by id")
    void update_updateCartera_success() throws NotFoundException {

        Mockito
                .when(carteraRepository.findById(Mockito.any(Integer.class)))
                .thenReturn(Optional.ofNullable(entity));

        Mockito
                .doNothing()
                .when(carteraMapper)
                .mergeToEntity(Mockito.any(CarteraEntity.class), Mockito.any(Cartera.class));

        Mockito
                .when(carteraMapper.entityToDomain(Mockito.any(CarteraEntity.class)))
                .thenReturn(domain);

        Mockito
                .when(carteraRepository.save(Mockito.any(CarteraEntity.class)))
                .thenReturn(entity);

        var result = carteraPort.update(domain);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(id, result.getId()));
    }

    @Test
    @DisplayName("Test for updating a record of cartera by id with an error called cartera not found")
    void update_updateCartera_error_cartera_not_found() throws NotFoundException {

        assertThatExceptionOfType(CarteraNotFoundException.class)
                .isThrownBy(() -> carteraPort.update(domain))
                .withMessage(CarteraErrorCodes.CARTERA_NOT_FOUND.getLocalizedMessage());
    }

    @Test
    @DisplayName("Test for finding a record of cartera by id")
    void find_findCarteraById_success(){

        Mockito
                .when(carteraRepository.findById(Mockito.any(Integer.class)))
                .thenReturn(Optional.ofNullable(entity));

        Mockito
                .when(carteraMapper.entityToDomain(Mockito.any(CarteraEntity.class)))
                .thenReturn(domain);

        var result = carteraPort.findById(id);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(id, result.getId()));
    }
}
