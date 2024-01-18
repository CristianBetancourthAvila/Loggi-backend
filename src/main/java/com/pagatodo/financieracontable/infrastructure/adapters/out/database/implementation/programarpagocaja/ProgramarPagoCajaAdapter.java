package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.programarpagocaja;

import com.pagatodo.financieracontable.domain.models.ProgramarPagoCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.programarpagocaja.ProgramarPagoCajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.ProgramarPagoCajaRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpagocaja.ProgramarPagoCajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class ProgramarPagoCajaAdapter implements ProgramarPagoCajaPort {

    private final ProgramarPagoCajaRepository programarPagoCajaRepository;
    private final ProgramarPagoCajaMapper programarPagoCajaMapper;
    @Override
    public ProgramarPagoCaja save(ProgramarPagoCaja programarPagoCaja) {
        ProgramarPagoCajaEntity programarPagoCajaEntity = programarPagoCajaRepository.save(programarPagoCajaMapper.domainToEntity(programarPagoCaja));
        return programarPagoCajaMapper.entityToDomain(programarPagoCajaEntity);
    }

    @Override
    public ProgramarPagoCaja findPPByProgramarPagoIdAndCajaId(Integer programarPagoId, BigInteger cajaId) {
        return programarPagoCajaMapper.entityToDomain(programarPagoCajaRepository.findByProgramarPagoIdAndCajaId(programarPagoId, cajaId));
    }

    @Override
    public void delete(ProgramarPagoCaja programarPagoCaja) {
        programarPagoCajaRepository.delete(programarPagoCajaMapper.domainToEntity(programarPagoCaja));
    }
}
