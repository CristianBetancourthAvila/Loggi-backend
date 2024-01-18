package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.ingreso;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AperturaCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.IngresoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ParametrizacionConceptoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.ingreso.IngresoMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.IngresoRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.ingreso.IngresoPort;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngresoAdapterTest {

    @Mock
    private IngresoMapper ingresoMapper;

    @Mock
    private IngresoRepository ingresoRepository;

    private IngresoPort ingresoPort;

    private static String cancellationReason;

    private static Ingreso domain;

    private static IngresoEntity entity;

    @BeforeAll
    static void configInitial() {
        cancellationReason = "Motivo de anulacion";

        CajaEntity cajaEntity = new CajaEntity();
        cajaEntity.setId(BigInteger.valueOf(1));

        AperturaCajaEntity aperturaCajaEntity = new AperturaCajaEntity();
        aperturaCajaEntity.setId(1L);
        aperturaCajaEntity.setCaja(cajaEntity);

        ParametrizacionConceptoEntity parametrizacionConceptoEntity = new ParametrizacionConceptoEntity();
        parametrizacionConceptoEntity.setId(1L);

        Caja caja = new Caja();
        caja.setId(BigInteger.valueOf(1));

        AperturaCaja aperturaCaja = new AperturaCaja();
        aperturaCaja.setId(1L);
        aperturaCaja.setCaja(caja);

        ParametrizacionConcepto parametrizacionConcepto = new ParametrizacionConcepto();
        parametrizacionConcepto.setId(1L);

        entity = new IngresoEntity();
        entity.setId(1);
        entity.setAperturaCaja(aperturaCajaEntity);
        entity.setParametrizacionConcepto(parametrizacionConceptoEntity);
        entity.setMedioPagoId(4);
        entity.setUsuarioTerceroId(5);
        entity.setHoraCreacion(LocalTime.now());
        entity.setValorRecibido(500000L);
        entity.setFechaCreacion(LocalDate.now());
        entity.setMotivoAnulacion("Motivo anulacion");
        entity.setObservaciones("Entrada de observaciones");

        domain = new Ingreso();
        domain.setId(1);
        domain.setHoraCreacion(LocalTime.now());
        domain.setFechaCreacion(LocalDate.of(2023, 10, 22));
        domain.setAperturaCaja(aperturaCaja);
        domain.setParametrizacionConcepto(parametrizacionConcepto);
        domain.setMedioPagoId(4);
        domain.setUsuarioTerceroId(8);
        domain.setValorRecibido(25000L);
        domain.setMotivoAnulacion("Motivo anulacion");
        domain.setObservaciones("Observaciones prueba");

    }

    @Test
    @DisplayName("Test for creating a record of ingreso ")
    void create_createIngreso_success() throws Exception {

        ingresoPort = new IngresoAdapter(ingresoMapper, ingresoRepository);

        when(ingresoMapper.domainToEntity(Mockito.any(Ingreso.class)))
                .thenReturn(entity);

        when(ingresoMapper.entityToDomain(Mockito.any(IngresoEntity.class)))
                .thenReturn(domain);

        when(ingresoRepository.save(Mockito.any(IngresoEntity.class)))
                .thenReturn(entity);

        var result = ingresoPort.save(domain);

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(domain.getId(), result.getId()));
    }

    @Test
    @DisplayName("Test for updating field caled motivoAnulacion of an specific ingreso")
    void updateCancellationReason_success() {

        ingresoPort = new IngresoAdapter(ingresoMapper, ingresoRepository);

        doNothing().when(ingresoRepository).updateCancellationReason(Mockito.any(Integer.class), Mockito.anyString());

        ingresoPort.updateCancellationReason(domain.getId(), cancellationReason);

        verify(ingresoRepository, times(1)).updateCancellationReason(domain.getId(), cancellationReason);
    }

    @Test
    @DisplayName("Test for finding a record of ingreso by id")
    void find_findById_success() {

        ingresoPort = new IngresoAdapter(ingresoMapper, ingresoRepository);

        when(ingresoMapper.entityToDomain(Mockito.any(IngresoEntity.class)))
                .thenReturn(domain);

        when(ingresoRepository.findById(Mockito.any(Integer.class)))
                .thenReturn(Optional.ofNullable(entity));

        var result = ingresoPort.findById(entity.getId());

        assertAll("resultado",
                () -> assertNotNull(result),
                () -> assertEquals(domain.getId(), result.getId()));
    }

    @Test
    @DisplayName("Test for finding ingresos by date, usuario, and caja")
    void findIngresosByDateAndUsuarioAndCaja_IngresosFound_Success() {
        ingresoPort = new IngresoAdapter(ingresoMapper, ingresoRepository);
        LocalDate date = LocalDate.now();
        BigInteger usuarioId = BigInteger.valueOf(2);
        BigInteger cajaId = BigInteger.valueOf(3);
        List<IngresoEntity> ingresoEntities = List.of(new IngresoEntity(), new IngresoEntity());
        List<Ingreso> expectedIngresos = List.of(new Ingreso(), new Ingreso());
        when(ingresoRepository.findByFechaCreacionAndUserIdAndCajaId(date, usuarioId, cajaId)).thenReturn(ingresoEntities);
        when(ingresoMapper.entitiesToDomains(ingresoEntities)).thenReturn(expectedIngresos);
        List<Ingreso> result = ingresoPort.findIngresosByDateAndUsuarioAndCaja(date, usuarioId, cajaId);
        assertEquals(expectedIngresos, result);
        verify(ingresoRepository, times(1)).findByFechaCreacionAndUserIdAndCajaId(date, usuarioId, cajaId);
    }

    @Test
    @DisplayName("Test for finding all ingresos by date range and libro diario filter")
    void findAllByDateBetweenAndLibroDiarioFilter_IngresosFound_Success() {
        ingresoPort = new IngresoAdapter(ingresoMapper, ingresoRepository);
        LocalDate dateOne = LocalDate.now().minusDays(7);
        LocalDate dateTwo = LocalDate.now();
        BigInteger usuarioId = BigInteger.valueOf(2);
        BigInteger cajaId = BigInteger.valueOf(3);
        LibroDiarioFilter libroDiarioFilter = new LibroDiarioFilter();
        libroDiarioFilter.setDateOne(dateOne);
        libroDiarioFilter.setDateTwo(dateTwo);
        libroDiarioFilter.setIdUsuario(usuarioId);
        libroDiarioFilter.setIdCaja(cajaId);
        List<IngresoEntity> ingresoEntities = List.of(new IngresoEntity(), new IngresoEntity());
        List<Ingreso> expectedIngresos = List.of(new Ingreso(), new Ingreso());
        when(ingresoRepository.findByFechaCreacionBetweenAndUserIdAndCajaId(dateOne, dateTwo, usuarioId, cajaId)).thenReturn(ingresoEntities);
        when(ingresoMapper.entitiesToDomains(ingresoEntities)).thenReturn(expectedIngresos);
        List<Ingreso> result = ingresoPort.findAllByDateBetweenAndLibroDiarioFilter(libroDiarioFilter);
        assertEquals(expectedIngresos, result);
        verify(ingresoRepository, times(1)).findByFechaCreacionBetweenAndUserIdAndCajaId(dateOne, dateTwo, usuarioId, cajaId);
    }
}
