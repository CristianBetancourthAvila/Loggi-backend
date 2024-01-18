package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.programarpago;

import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.TerceroNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.filter.ProgramarPagoFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.programarpago.ProgramarPagoMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.ProgramarPagoRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramarPagoAdapter implements ProgramarPagoPort {

    private final ProgramarPagoRepository programarPagoRepository;
    private final ProgramarPagoMapper programarPagoMapper;

    @Override
    public ProgramarPago save(ProgramarPago programarPago) {
        return programarPagoMapper.entityToDomain(programarPagoRepository.save(programarPagoMapper.domainToEntity(programarPago)));
    }

    @Override
    public ProgramarPago findById(Integer id) {
        ProgramarPagoEntity programarPago = programarPagoRepository.findById(id).orElse(null);
        return programarPagoMapper.entityToDomain(programarPago);
    }

    @Override
    public List<ProgramarPago> findAllByTerceroId(BigInteger terceroId) throws TerceroNotFoundException {
        List<ProgramarPagoEntity> programarPagos = programarPagoRepository.findAllByCriteria(terceroId);
        if (programarPagos.isEmpty()) {
            TerceroNotFoundException terceroNotFoundException = new TerceroNotFoundException();
            terceroNotFoundException.addParams(terceroId);
            throw terceroNotFoundException;
        }
        return programarPagoMapper.entitiesToDomains(programarPagos);
    }

    @Override
    public List<ProgramarPago> findWithFilter(ProgramarPagoFilter programarPagoFilter) {
        List<ProgramarPagoEntity> programarPagos = programarPagoRepository.findWithFilter(programarPagoFilter.getEstado(), programarPagoFilter.getFechaAplicacion(), programarPagoFilter.getCondicion(), programarPagoFilter.getConceptoEgreso());
        return programarPagoMapper.entitiesToDomains(programarPagos);
    }

    @Override
    public ProgramarPago update(ProgramarPago programarPago) throws ProgramarPagoNotFoundException {
        ProgramarPagoNotFoundException errorNotFound =  new ProgramarPagoNotFoundException();
        errorNotFound.addParams(programarPago.getId());
        ProgramarPagoEntity target = programarPagoRepository.findById(programarPago.getId()).orElseThrow(() -> errorNotFound);
        programarPagoMapper.mergeToEntity(target, programarPago);
        return programarPagoMapper.entityToDomain(programarPagoRepository.save(target));
    }

    @Override
    public void updateStatus(Integer id, Estado newStatus) {
        programarPagoRepository.updateStatus(id,newStatus);
    }
}