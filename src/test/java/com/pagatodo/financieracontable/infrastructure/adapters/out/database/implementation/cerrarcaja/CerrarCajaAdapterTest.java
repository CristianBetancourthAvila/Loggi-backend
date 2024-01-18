package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.cerrarcaja;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.CerrarCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AperturaCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CerrarCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.cerrarcaja.CerrarCajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.CerrarCajaRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.cerrarcaja.CerrarCajaPort;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CerrarCajaAdapterTest {

    @Mock
    private CerrarCajaMapper cerrarCajaMapper;

    @Mock
    private CerrarCajaRepository cerrarCajaRepository;

    private CerrarCajaPort cerrarCajaPort;

    private static Long id = 1L;

    private static CerrarCaja domain;

    private static CerrarCajaEntity entity;

    @BeforeEach
    void setUp() {
        cerrarCajaPort = new CerrarCajaAdapter(cerrarCajaMapper, cerrarCajaRepository);

        AperturaCaja aperturaCaja = new AperturaCaja();
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        aperturaCaja.setId(id);
        aperturaCaja.setCaja(caja);

        AperturaCajaEntity aperturaCajaEntity = new AperturaCajaEntity();
        CajaEntity cajaEntity = new CajaEntity();
        cajaEntity.setId(BigInteger.ONE);
        aperturaCajaEntity.setId(id);
        aperturaCajaEntity.setCaja(cajaEntity);

        domain = new CerrarCaja();
        domain.setId(id);
        domain.setAperturaCaja(aperturaCaja);
        domain.setNumeroBolsa("123");
        domain.setMonedas(1000L);
        domain.setBilletes(1000L);
        domain.setHoraCierre(LocalTime.now());
        domain.setFechaCierre(LocalDate.now());
        domain.setSaldoFinal(2000L);

        entity = new CerrarCajaEntity();
        entity.setId(id);
        entity.setAperturaCaja(aperturaCajaEntity);
        entity.setNumeroBolsa("123");
        entity.setMonedas(1000L);
        entity.setBilletes(1000L);
        entity.setHoraCierre(LocalTime.now());
        entity.setFechaCierre(LocalDate.now());
        entity.setSaldoFinal(2000L);
    }

    @Test
    @DisplayName("Test for creating a record of cerrarCaja ")
    void create_createCerrarCaja_success() {

        Mockito
                .when(cerrarCajaMapper.domainToEntity(Mockito.any(CerrarCaja.class)))
                .thenReturn(entity);

        Mockito
                .when(cerrarCajaRepository.save(Mockito.any(CerrarCajaEntity.class)))
                .thenReturn(entity);

        Mockito
                .when(cerrarCajaMapper.entityToDomain(Mockito.any(CerrarCajaEntity.class)))
                .thenReturn(domain);

        var result = cerrarCajaPort.create(domain);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(domain.getId(), result.getId()));
    }
}
