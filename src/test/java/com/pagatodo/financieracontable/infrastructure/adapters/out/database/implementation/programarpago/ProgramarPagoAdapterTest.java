package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.programarpago;

import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.filter.ProgramarPagoFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.programarpago.ProgramarPagoMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.ProgramarPagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoAdapterTest {

    @Mock
    private ProgramarPagoRepository programarPagoRepository;

    @Mock
    private ProgramarPagoMapper programarPagoMapper;

    @InjectMocks
    private ProgramarPagoAdapter programarPagoAdapter;

    private ProgramarPagoEntity programarPagoEntity;
    private ProgramarPago programarPago;

    @BeforeEach
    void setUp() {
        programarPagoAdapter = new ProgramarPagoAdapter(programarPagoRepository, programarPagoMapper);
        programarPagoEntity = new ProgramarPagoEntity();
        programarPagoEntity.setId(1);
        programarPagoEntity.setEstado(Estado.INACTIVO);

        programarPago = new ProgramarPago();
        programarPago.setId(1);
        programarPago.setEstado(Estado.INACTIVO);
    }

    @Test
    @DisplayName("Test for save method")
    void saveProgramarPago_ReturnSavedProgramarPago() {
        when(programarPagoMapper.domainToEntity(programarPago)).thenReturn(programarPagoEntity);
        when(programarPagoRepository.save(any(ProgramarPagoEntity.class))).thenReturn(programarPagoEntity);
        when(programarPagoMapper.entityToDomain(programarPagoEntity)).thenReturn(programarPago);
        ProgramarPago savedProgramarPago = programarPagoAdapter.save(programarPago);
        assertEquals(programarPago, savedProgramarPago);
    }

    @Test
    @DisplayName("Test for findById method")
    void findById_ProgramarPagoFound_ReturnProgramarPago() {
        when(programarPagoRepository.findById(anyInt())).thenReturn(Optional.of(programarPagoEntity));
        when(programarPagoMapper.entityToDomain(programarPagoEntity)).thenReturn(programarPago);
        ProgramarPago result = programarPagoAdapter.findById(1);
        assertEquals(programarPago, result);
    }

    @Test
    @DisplayName("Test for findAllByTerceroId method when no ProgramarPagos found")
    void findAllByTerceroId_NoProgramarPagosFound_ThrowException() {
        BigInteger terceroId = BigInteger.ONE;
        when(programarPagoRepository.findAllByCriteria(terceroId)).thenReturn(Collections.emptyList());
        assertThrows(
                TerceroNotFoundException.class,
                () -> programarPagoAdapter.findAllByTerceroId(terceroId)
        );
        assertThatExceptionOfType(TerceroNotFoundException.class)
                .isThrownBy(() -> programarPagoAdapter.findAllByTerceroId(terceroId))
                .withMessage(ProgramarPagoErrorCodes.TERCERO_NOT_FOUND.getLocalizedMessage());
    }

    @Test
    @DisplayName("Test for findAllByTerceroId method when ProgramarPagos found")
    void findAllByTerceroId_ProgramarPagosFound_ReturnProgramarPagos() throws TerceroNotFoundException {
        BigInteger terceroId = BigInteger.ONE;
        List<ProgramarPagoEntity> programarPagoEntities = Collections.singletonList(programarPagoEntity);
        when(programarPagoRepository.findAllByCriteria(terceroId)).thenReturn(programarPagoEntities);
        when(programarPagoMapper.entitiesToDomains(programarPagoEntities)).thenReturn(Collections.singletonList(programarPago));
        List<ProgramarPago> result = programarPagoAdapter.findAllByTerceroId(terceroId);
        assertEquals(Collections.singletonList(programarPago), result);
    }

    @Test
    @DisplayName("Test for findWithFilter method")
    void findProgramarPagoWithFilter() {
        ProgramarPagoFilter filter = new ProgramarPagoFilter();
        ProgramarPagoEntity programarPagoEntity = new ProgramarPagoEntity();
        ProgramarPago programarPago = new ProgramarPago();
        when(programarPagoRepository.findWithFilter(any(), any(), any(), any())).thenReturn(Collections.singletonList(programarPagoEntity));
        when(programarPagoMapper.entitiesToDomains(anyList())).thenReturn(Collections.singletonList(programarPago));
        List<ProgramarPago> result = programarPagoAdapter.findWithFilter(filter);
        assertEquals(Collections.singletonList(programarPago), result);
    }

    @Test
    @DisplayName("Test for update method")
    void updateProgramarPago_ReturnUpdatedProgramarPago() throws ProgramarPagoNotFoundException {
        when(programarPagoRepository.findById(anyInt())).thenReturn(Optional.of(programarPagoEntity));
        when(programarPagoRepository.save(any(ProgramarPagoEntity.class))).thenReturn(programarPagoEntity);
        when(programarPagoMapper.entityToDomain(programarPagoEntity)).thenReturn(programarPago);
        ProgramarPago updatedProgramarPago = programarPagoAdapter.update(programarPago);
        assertEquals(programarPago, updatedProgramarPago);
    }

    @Test
    @DisplayName("Test for updateStatus method")
    void updateStatusProgramarPago_StatusUpdatedSuccessfully() {
        programarPagoAdapter.updateStatus(programarPago.getId(), programarPago.getEstado());
        verify(programarPagoRepository).updateStatus(1, Estado.INACTIVO);
    }
}
