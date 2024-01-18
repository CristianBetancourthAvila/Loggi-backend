package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.aperturacaja;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AperturaCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.aperturacaja.AperturaCajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.AperturaCajaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AperturaCajaAdapterTest {

    @Mock
    private AperturaCajaMapper aperturaCajaMapper;

    @Mock
    private AperturaCajaRepository aperturaCajaRepository;

    @InjectMocks
    private AperturaCajaAdapter aperturaCajaPort;

    private static Long id = 1L;

    private static AperturaCaja domain;

    private static AperturaCajaEntity entity;


    @BeforeEach
    void setUp() {

        aperturaCajaPort = new AperturaCajaAdapter(aperturaCajaMapper,aperturaCajaRepository);

        BigInteger cajaId = BigInteger.valueOf(1);
        Caja caja = new Caja();
        caja.setId(cajaId);

        CajaEntity cajaEntity = new CajaEntity();
        cajaEntity.setId(cajaId);

        domain = new AperturaCaja();
        domain.setId(id);
        domain.setUsuarioId(BigInteger.valueOf(4));
        domain.setCaja(caja);
        domain.setEstado(true);
        domain.setSaldoInicial(50000L);
        domain.setBilletes(30000L);
        domain.setMonedas(20000L);

        entity = new AperturaCajaEntity();
        entity.setId(id);
        entity.setUsuarioId(4L);
        entity.setCaja(cajaEntity);
        entity.setEstado(true);
        entity.setSaldoInicial(50000L);
        entity.setBilletes(30000L);
        entity.setMonedas(20000L);
    }

    @Test
    @DisplayName("Test for creating a record of aperturaCaja ")
    void create_createAperturaCaja_success() {
        when(aperturaCajaMapper.domainToEntity((any(AperturaCaja.class))))
                .thenReturn(entity);
        when(aperturaCajaMapper.entityToDomain((any(AperturaCajaEntity.class))))
                .thenReturn(domain);
        when(aperturaCajaRepository.save(any(AperturaCajaEntity.class)))
                .thenReturn(entity);
        var result = aperturaCajaPort.create(domain);
        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(id, result.getId()));

    }

    @Test
    @DisplayName("Test for getLastRecord method")
    void getLastRecord_ReturnAperturaCaja() {
        BigInteger cajaId = BigInteger.valueOf(1);
        AperturaCajaEntity aperturaCajaEntity = new AperturaCajaEntity();
        AperturaCaja aperturaCaja = new AperturaCaja();
        when(aperturaCajaRepository.getLastRecord(cajaId)).thenReturn(aperturaCajaEntity);
        when(aperturaCajaMapper.entityToDomain(aperturaCajaEntity)).thenReturn(aperturaCaja);
        AperturaCaja result = aperturaCajaPort.getLastRecord(cajaId);
        assertEquals(aperturaCaja, result);
    }

    @Test
    @DisplayName("Test for findById method")
    void findById_ReturnAperturaCaja() {
        Long id = 1L;
        AperturaCajaEntity aperturaCajaEntity = new AperturaCajaEntity();
        AperturaCaja aperturaCaja = new AperturaCaja();
        when(aperturaCajaRepository.findById(id)).thenReturn(java.util.Optional.of(aperturaCajaEntity));
        when(aperturaCajaMapper.entityToDomain(any())).thenReturn(aperturaCaja);
        AperturaCaja result = aperturaCajaPort.findById(id);
        assertEquals(aperturaCaja, result);
    }

    @Test
    @DisplayName("Test for updating a record of aperturaCaja ")
    void update_status_success() {
        doNothing()
                .when(aperturaCajaRepository).updateStatus(Mockito.any(Long.class), Mockito.anyBoolean());

        aperturaCajaPort.updateStatus(domain.getId(), false);

        Mockito.verify(aperturaCajaRepository, Mockito.times(1)).updateStatus(domain.getId(), false);
    }
}
