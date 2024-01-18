package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.caja.CajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.CajaProjection;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.CajaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CajaAdapterTest {

    @Mock
    private CajaRepository cajaRepository;

    @Mock
    private CajaMapper cajaMapper;

    private CajaAdapter cajaAdapter;

    private static CajaProjection cajaProjection;

    private static CajaEntity cajaEntity1;
    private static CajaEntity cajaEntity2;
    private static Caja caja1;
    private static Caja caja2;

    @BeforeAll
    static void configInitial() {
        cajaEntity1 = new CajaEntity();
        cajaEntity1.setId(BigInteger.valueOf(1L));
        cajaEntity1.setNombreCaja("Caja 1");
        cajaEntity2 = new CajaEntity();
        cajaEntity2.setId(BigInteger.valueOf(2L));
        cajaEntity2.setNombreCaja("Caja 2");

        caja1 = new Caja();
        caja1.setId(BigInteger.valueOf(1));
        caja1.setNombreCaja("Caja 1");

        caja2 = new Caja();
        caja2.setId(BigInteger.valueOf(2));
        caja2.setNombreCaja("Caja 2");

        cajaProjection = new CajaProjection(BigInteger.ONE, "", "Caja 1");
    }

    @BeforeEach
    void setUp() {
        cajaAdapter = new CajaAdapter(cajaRepository, cajaMapper);
    }

    @Test
    @DisplayName("Test for saveAll method")
    void saveAll_SaveMultipleCajas_Success() {
        when(cajaRepository.saveAll(any())).thenReturn(Arrays.asList(cajaEntity1, cajaEntity2));
        List<Caja> cajaList = Arrays.asList(caja1, caja2);
        Mockito.when(cajaMapper.domainsToEntities(anyList())).thenReturn(List.of(cajaEntity1, cajaEntity2));
        Mockito.when(cajaMapper.entitiesToDomains(anyList())).thenReturn(cajaList);
        List<Caja> savedCajas = cajaAdapter.saveAll(cajaList);
        assertEquals(cajaList, savedCajas);
    }

    @Test
    @DisplayName("Test for findById method")
    void findById_CajaExists_ReturnCaja() {
        BigInteger id = BigInteger.valueOf(1L);
        when(cajaRepository.findById(id)).thenReturn(Optional.of(cajaEntity1));
        when(cajaMapper.entityToDomain(cajaEntity1)).thenReturn(caja1);
        Caja result = cajaAdapter.findById(id);
        assertEquals(caja1, result);
    }

    @Test
    @DisplayName("Test for save method")
    void save_CajaSaved_Success() {
        when(cajaMapper.domainToEntity(caja1)).thenReturn(cajaEntity1);
        when(cajaRepository.save(cajaEntity1)).thenReturn(cajaEntity1);
        when(cajaMapper.entityToDomain(cajaEntity1)).thenReturn(caja1);
        Caja result = cajaAdapter.save(caja1);
        assertEquals(caja1, result);
    }

    @Test
    @DisplayName("Test for updateStatus method")
    void updateStatus_CajaStatusUpdated_Success() {
        BigInteger id = BigInteger.valueOf(1L);
        Estado estado = Estado.INACTIVO;
        Mockito.doNothing().when(cajaRepository).updateStatus(id, estado);
        cajaAdapter.updateStatus(id, estado);
        Mockito.verify(cajaRepository, Mockito.times(1)).updateStatus(id, estado);
    }

    @Test
    @DisplayName("Test for findAllByCodigoCaja method")
    void findAllByCodigoCaja_FilteredCode_ReturnCajas() {
        String code = "CAJA001";
        List<CajaEntity> cajasFiltered = Arrays.asList(cajaEntity1, cajaEntity2);
        when(cajaRepository.findAllByFilter(code,1L)).thenReturn(cajasFiltered);
        List<Caja> expectedCajas = Arrays.asList(caja1, caja2);
        List<CajaEntity> expectedEntities = Arrays.asList(cajaEntity1, cajaEntity2);
        Mockito.when(cajaMapper.entitiesToDomains(expectedEntities)).thenReturn(expectedCajas);
        List<Caja> foundCajas = cajaAdapter.findAllByCodeAndPVTId(code, 1L);
        assertEquals(expectedCajas, foundCajas);
    }

    @Test
    @DisplayName("Test for findAllByDateBetween method")
    void findAllByDateBetween_ReturnCajas() throws CajaNotFoundException {
        LocalDate from = LocalDate.of(2023, 10, 1);
        LocalDate to = LocalDate.of(2023, 10, 14);
        List<CajaEntity> cajasEntities = Collections.singletonList(cajaEntity1);
        when(cajaRepository.findByFechaCreacionBetween(from, to)).thenReturn(cajasEntities);
        List<Caja> expectedCajas = Collections.singletonList(caja1);
        Mockito.when(cajaMapper.entitiesToDomains(cajasEntities)).thenReturn(expectedCajas);
        List<Caja> foundCajas = cajaAdapter.findAllByDateBetween(from, to);
        assertEquals(expectedCajas, foundCajas);
    }

    @Test
    @DisplayName("Test for findAllByDateBetween method with no cajas found")
    void findAllByDateBetween_NoCajasFound_ThrowCajaNotFoundException() {
        LocalDate from = LocalDate.of(2023, 10, 1);
        LocalDate to = LocalDate.of(2023, 10, 14);
        when(cajaRepository.findByFechaCreacionBetween(from, to)).thenReturn(Collections.emptyList());
        assertThrows(CajaNotFoundException.class, () -> cajaAdapter.findAllByDateBetween(from, to));
    }

    @Test
    @DisplayName("Test for findAllByDateYearAndMonth method")
    void findAllByDateYearAndMonth_ReturnCajas() throws CajaNotFoundException {
        int year = 2023;
        int month = 10;
        List<CajaEntity> cajasEntities = Collections.singletonList(cajaEntity1);
        when(cajaRepository.findByMonthAndYear(year, month)).thenReturn(cajasEntities);
        List<Caja> expectedCajas = Collections.singletonList(caja1);
        Mockito.when(cajaMapper.entitiesToDomains(cajasEntities)).thenReturn(expectedCajas);
        List<Caja> foundCajas = cajaAdapter.findAllByDateYearAndMonth(year, month);
        assertEquals(expectedCajas, foundCajas);
    }

    @Test
    @DisplayName("Test for findAllByDateYearAndMonth method with no cajas found")
    void findAllByDateYearAndMonth_NoCajasFound_ThrowCajaNotFoundException() {
        int year = 2023;
        int month = 4;
        when(cajaRepository.findByMonthAndYear(year, month)).thenReturn(Collections.emptyList());
        assertThrows(CajaNotFoundException.class, () -> cajaAdapter.findAllByDateYearAndMonth(year, month));
    }

    @Test
    @DisplayName("Test for getCajaByPdvTerminalId method")
    void getCajaByPdvTerminalId_CajaExists_ReturnCaja() {
        BigInteger pdvTerminalId = BigInteger.valueOf(2L);
        when(cajaRepository.getCajaByPdvTerminalId(pdvTerminalId)).thenReturn(cajaEntity2);
        when(cajaMapper.entityToDomain(cajaEntity2)).thenReturn(caja2);
        Caja result = cajaAdapter.getCajaByPdvTerminalId(pdvTerminalId);
        assertEquals(caja2, result);
    }

    @Test
    @DisplayName("Test for findCajasByProgramarPagoid method with cajas found")
    void findCajasByProgramarPagoid_CajasFound_ReturnCajas() throws CajaNotFoundException {
        Integer programarPagoId = 1;
        List<CajaEntity> cajaEntities = Arrays.asList(new CajaEntity(), new CajaEntity());
        when(cajaRepository.findCajasByProgramarPagoId(programarPagoId)).thenReturn(cajaEntities);
        List<Caja> expectedCajas = Arrays.asList(new Caja(), new Caja());
        when(cajaMapper.entitiesToDomains(cajaEntities)).thenReturn(expectedCajas);
        List<Caja> result = cajaAdapter.findCajasByProgramarPagoid(programarPagoId);
        assertEquals(expectedCajas, result);
    }

    @Test
    @DisplayName("Test for findCajasByProgramarPagoid method with no cajas found")
    void findCajasByProgramarPagoid_NoCajasFound_ThrowCajaNotFoundException() {
        Integer programarPagoId = 1;
        when(cajaRepository.findCajasByProgramarPagoId(programarPagoId)).thenReturn(Collections.emptyList());
        assertThrows(CajaNotFoundException.class, () -> cajaAdapter.findCajasByProgramarPagoid(programarPagoId));
    }

    @Test
    @DisplayName("Test for findAllByPVTerminalId method")
    void findAllByPVTerminalId_ReturnCajas() {
        List<Long> pdvTerminalIds = Arrays.asList(1L, 2L);
        when(cajaRepository.findAllCajasByPdvTerminalIds(anyList()))
                .thenReturn(Arrays.asList(new CajaEntity(), new CajaEntity()));
        when(cajaMapper.entitiesToDomains(anyList()))
                .thenReturn(Arrays.asList(new Caja(), new Caja()));
        List<Caja> result = cajaAdapter.findAllByPVTerminalId(pdvTerminalIds);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Test for findAllByMatchesAndStatus method")
    void findAllByMatchesAndStatus_ReturnCajas() {
        String filterText = "test";
        Estado status = Estado.ACTIVO;
        List<CajaProjection> cajaProjections = Collections.singletonList(cajaProjection);
        when(cajaRepository.findAllByMatchesAndStatus(filterText, status)).thenReturn(cajaProjections);
        List<Caja> expectedCajas = Collections.singletonList(caja1);
        when(cajaMapper.cajaProjectionsToDomains(cajaProjections)).thenReturn(expectedCajas);
        List<Caja> result = cajaAdapter.findAllByMatchesAndStatus(filterText, status);
        assertEquals(expectedCajas, result);
    }

    @Test
    @DisplayName("Test for findByTop10AndMatchesAndStatus method")
    void findByTop10AndMatchesAndStatus_ReturnCajas() {
        String filterText = "test";
        Estado status = Estado.ACTIVO;
        List<CajaProjection> cajaProjections = Collections.singletonList(cajaProjection);
        when(cajaRepository.findByTop10AndMatchesAndStatus(filterText, status)).thenReturn(cajaProjections);
        List<Caja> expectedCajas = Collections.singletonList(caja1);
        when(cajaMapper.cajaProjectionsToDomains(cajaProjections)).thenReturn(expectedCajas);
        List<Caja> result = cajaAdapter.findByTop10AndMatchesAndStatus(filterText, status);
        assertEquals(expectedCajas, result);
    }

    @Test
    @DisplayName("Test for update method")
    void update_CajaUpdated_Success() throws CajaIdNotFoundException {
        Caja caja = new Caja();
        caja.setId(BigInteger.valueOf(1L));
        caja.setNombreCaja("Updated Caja");
        CajaEntity existingCajaEntity = new CajaEntity();
        existingCajaEntity.setId(BigInteger.valueOf(1L));
        existingCajaEntity.setNombreCaja("Old Caja");
        when(cajaRepository.findById(caja.getId())).thenReturn(Optional.of(existingCajaEntity));
        when(cajaRepository.save(any())).thenReturn(existingCajaEntity);
        when(cajaMapper.entityToDomain(existingCajaEntity)).thenReturn(caja);
        Caja updatedCaja = cajaAdapter.update(caja);
        assertEquals(caja.getId(), updatedCaja.getId());
        assertEquals(caja.getNombreCaja(), updatedCaja.getNombreCaja());
    }

}
