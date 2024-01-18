package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.egresocaja;

import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.EgresoCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.egresocaja.EgresoCajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.EgresoCajaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EgresoCajaAdapterTest {

    @Mock
    private EgresoCajaRepository egresoCajaRepository;

    @Mock
    private EgresoCajaMapper egresoCajaMapper;

    @InjectMocks
    private EgresoCajaAdapter egresoCajaAdapter;

    private static EgresoCajaEntity egresoCajaEntity;
    private static EgresoCaja egresoCaja;

    @BeforeAll
    static void configInitial() {
        egresoCajaEntity = new EgresoCajaEntity();
        egresoCajaEntity.setId(1);
        egresoCajaEntity.setUsuarioTerceroId(BigInteger.ONE);
        egresoCajaEntity.setProgramarPago(new ProgramarPagoEntity());
        egresoCajaEntity.setMotivoAnulacion("Motivo de anulación");
        egresoCajaEntity.setFechaCreacion(LocalDate.now());

        egresoCaja = new EgresoCaja();
        egresoCaja.setId(1);
        egresoCaja.setUsuarioTerceroId(BigInteger.ONE);
        egresoCaja.setProgramarPago(new ProgramarPago());
        egresoCaja.setMotivoAnulacion("Motivo de anulación");
        egresoCaja.setFechaCreacion(LocalDate.now());
    }

    @Test
    @DisplayName("Test for save method")
    void save_EgresoCajaSaved_Success() {
        when(egresoCajaMapper.domainToEntity(egresoCaja)).thenReturn(egresoCajaEntity);
        when(egresoCajaRepository.save(any(EgresoCajaEntity.class))).thenReturn(egresoCajaEntity);
        when(egresoCajaMapper.entityToDomain(egresoCajaEntity)).thenReturn(egresoCaja);
        EgresoCaja result = egresoCajaAdapter.save(egresoCaja);
        assertEquals(egresoCaja, result);
    }

    @Test
    @DisplayName("Test for updateMotivo method")
    void updateMotivo_EgresoCajaUpdated_Success() throws EgresoCajaNotFoundException {
        int id = 1;
        String motivoAnulacion = "Nuevo motivo de anulación";
        EgresoCajaEntity egresoCajaEntity = new EgresoCajaEntity();
        egresoCajaEntity.setId(id);
        egresoCajaEntity.setMotivoAnulacion("Motivo de anulación existente");
        when(egresoCajaRepository.findById(id)).thenReturn(Optional.of(egresoCajaEntity));
        egresoCajaAdapter.updateMotivo(id, motivoAnulacion);
        verify(egresoCajaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test for updateMotivo method Exception")
    void updateMotivo_EgresoCajaUpdated_Exception() {
        when(egresoCajaRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        assertThrows(EgresoCajaNotFoundException.class, () -> egresoCajaAdapter.updateMotivo(1, "Motivo de anulación"));
    }

    @Test
    @DisplayName("Test for findById method - EgresoCaja found")
    void findById_EgresoCajaFound_Success() throws EgresoCajaNotFoundException {
        int id = 1;
        when(egresoCajaRepository.findById(id)).thenReturn(Optional.of(egresoCajaEntity));
        when(egresoCajaAdapter.findById(id)).thenReturn(egresoCaja);
        EgresoCaja result = egresoCajaAdapter.findById(id);
        Assertions.assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Motivo de anulación", result.getMotivoAnulacion());
    }

    @Test
    @DisplayName("Test for findById method - EgresoCaja not found")
    void findById_EgresoCajaNotFound_ExceptionThrown() {
        int id = 1;
        when(egresoCajaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EgresoCajaNotFoundException.class, () -> egresoCajaAdapter.findById(id));
        verify(egresoCajaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test for findEgresosByDateAndUsuarioAndCaja method")
    void findEgresosByDateAndUsuarioAndCaja_EgresosFound_Success() {
        LocalDate date = LocalDate.now();
        BigInteger usuarioId = BigInteger.valueOf(2);
        BigInteger cajaId = BigInteger.valueOf(3);
        List<EgresoCajaEntity> egresoCajaEntities = List.of(new EgresoCajaEntity(), new EgresoCajaEntity());
        List<EgresoCaja> expectedEgresos = List.of(new EgresoCaja(), new EgresoCaja());
        when(egresoCajaRepository.findByFechaCreacionAndUserIdAndCajaId(date, usuarioId, cajaId)).thenReturn(egresoCajaEntities);
        when(egresoCajaMapper.entitiesToDomains(egresoCajaEntities)).thenReturn(expectedEgresos);
        List<EgresoCaja> result = egresoCajaAdapter.findEgresosByDateAndUsuarioAndCaja(date, usuarioId, cajaId);
        assertEquals(expectedEgresos, result);
        verify(egresoCajaRepository, times(1)).findByFechaCreacionAndUserIdAndCajaId(date, usuarioId, cajaId);
    }

    @Test
    @DisplayName("Test for findAllByDateBetweenAndLibroDiarioFilter method")
    void findAllByDateBetweenAndLibroDiarioFilter_EgresosFound_Success() {
        LocalDate dateOne = LocalDate.now().minusDays(7);
        LocalDate dateTwo = LocalDate.now();
        BigInteger usuarioId = BigInteger.valueOf(2);
        BigInteger cajaId = BigInteger.valueOf(3);
        LibroDiarioFilter libroDiarioFilter = new LibroDiarioFilter();
        libroDiarioFilter.setDateOne(dateOne);
        libroDiarioFilter.setDateTwo(dateTwo);
        libroDiarioFilter.setIdUsuario(usuarioId);
        libroDiarioFilter.setIdCaja(cajaId);
        List<EgresoCajaEntity> egresoCajaEntities = Arrays.asList(new EgresoCajaEntity(), new EgresoCajaEntity());
        List<EgresoCaja> expectedEgresos = Arrays.asList(new EgresoCaja(), new EgresoCaja());
        when(egresoCajaRepository.findByFechaCreacionBetweenAndUserIdAndCajaId(dateOne, dateTwo, usuarioId, cajaId)).thenReturn(egresoCajaEntities);
        when(egresoCajaMapper.entitiesToDomains(egresoCajaEntities)).thenReturn(expectedEgresos);
        List<EgresoCaja> result = egresoCajaAdapter.findAllByDateBetweenAndLibroDiarioFilter(libroDiarioFilter);
        assertEquals(expectedEgresos, result);
        verify(egresoCajaRepository, times(1)).findByFechaCreacionBetweenAndUserIdAndCajaId(dateOne, dateTwo, usuarioId, cajaId);
    }
}
