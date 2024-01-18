package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.programarpagocaja;

import com.pagatodo.financieracontable.domain.models.ProgramarPagoCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.programarpagocaja.ProgramarPagoCajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.ProgramarPagoCajaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoCajaAdapterTest {

    @Mock
    private ProgramarPagoCajaRepository programarPagoCajaRepository;

    @Mock
    private ProgramarPagoCajaMapper programarPagoCajaMapper;

    @InjectMocks
    private ProgramarPagoCajaAdapter programarPagoCajaAdapter;

    private ProgramarPagoCajaEntity programarPagoCajaEntity;
    private ProgramarPagoCaja programarPagoCaja;

    @BeforeEach
    void setUp() {
        programarPagoCajaAdapter = new ProgramarPagoCajaAdapter(programarPagoCajaRepository, programarPagoCajaMapper);
        programarPagoCajaEntity = new ProgramarPagoCajaEntity();
        programarPagoCajaEntity.setId(1);

        programarPagoCaja = new ProgramarPagoCaja();
        programarPagoCaja.setId(1);
    }

    @Test
    @DisplayName("Test for save method")
    void saveProgramarPagoCaja_ReturnSavedProgramarPagoCaja() {
        when(programarPagoCajaMapper.domainToEntity(programarPagoCaja)).thenReturn(programarPagoCajaEntity);
        when(programarPagoCajaRepository.save(any(ProgramarPagoCajaEntity.class))).thenReturn(programarPagoCajaEntity);
        when(programarPagoCajaMapper.entityToDomain(programarPagoCajaEntity)).thenReturn(programarPagoCaja);
        ProgramarPagoCaja savedProgramarPagoCaja = programarPagoCajaAdapter.save(programarPagoCaja);
        assertEquals(programarPagoCaja, savedProgramarPagoCaja);
    }

    @Test
    @DisplayName("Test for findPPByProgramarPagoIdAndCajaId method")
    void findPPByProgramarPagoIdAndCajaId_ReturnProgramarPagoCaja() {
        Integer programarPagoId = 1;
        BigInteger cajaId = BigInteger.valueOf(123);
        when(programarPagoCajaRepository.findByProgramarPagoIdAndCajaId(anyInt(), any(BigInteger.class)))
                .thenReturn(programarPagoCajaEntity);
        when(programarPagoCajaMapper.entityToDomain(any(ProgramarPagoCajaEntity.class)))
                .thenReturn(programarPagoCaja);
        ProgramarPagoCaja foundProgramarPagoCaja = programarPagoCajaAdapter.findPPByProgramarPagoIdAndCajaId(programarPagoId, cajaId);
        assertEquals(programarPagoCaja, foundProgramarPagoCaja);
    }

    @Test
    @DisplayName("Test for delete method")
    void deleteProgramarPagoCaja() {
        when(programarPagoCajaMapper.domainToEntity(any(ProgramarPagoCaja.class)))
                .thenReturn(programarPagoCajaEntity);
        programarPagoCajaAdapter.delete(programarPagoCaja);
        verify(programarPagoCajaRepository).delete(programarPagoCajaEntity);
    }
}
