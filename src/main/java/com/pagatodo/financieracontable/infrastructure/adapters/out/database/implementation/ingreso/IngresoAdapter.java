package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.ingreso;

import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.IngresoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.ingreso.IngresoMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.IngresoRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.ingreso.IngresoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IngresoAdapter implements IngresoPort {

    private final IngresoMapper ingresoMapper;

    private final IngresoRepository ingresoRepository;

    @Override
    public Ingreso save(Ingreso ingreso) {

        IngresoEntity ingresoEntity = ingresoMapper.domainToEntity(ingreso);
        IngresoEntity ingresoSaved = ingresoRepository.save(ingresoEntity);

        return ingresoMapper.entityToDomain(ingresoSaved);
    }

    @Override
    public void updateCancellationReason(Integer id, String cancellationReason) {ingresoRepository.updateCancellationReason(id, cancellationReason);}

    @Override
    public Ingreso findById(Integer id) {return ingresoMapper.entityToDomain(ingresoRepository.findById(id).orElse(null));}

    @Override
    public List<Ingreso> findIngresosByDateAndUsuarioAndCaja(LocalDate date, BigInteger usuarioId, BigInteger cajaId) {
        return ingresoMapper.entitiesToDomains(ingresoRepository.findByFechaCreacionAndUserIdAndCajaId(date,usuarioId,cajaId));
    }

    @Override
    public List<Ingreso> findAllByDateBetweenAndLibroDiarioFilter(LibroDiarioFilter libroDiarioFilter) {
        List<IngresoEntity> ingresoEntities = ingresoRepository.findByFechaCreacionBetweenAndUserIdAndCajaId(libroDiarioFilter.getDateOne(), libroDiarioFilter.getDateTwo(), libroDiarioFilter.getIdUsuario(), libroDiarioFilter.getIdCaja());
        return ingresoMapper.entitiesToDomains(ingresoEntities);
    }
}
