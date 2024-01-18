package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.egresocaja;

import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.EgresoCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.egresocaja.EgresoCajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.EgresoCajaRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja.EgresoCajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EgresoCajaAdapter implements EgresoCajaPort {

    private final EgresoCajaRepository egresoCajaRepository;
    private final EgresoCajaMapper egresoCajaMapper;
    @Override
    public EgresoCaja save(EgresoCaja egresoCaja) {
        EgresoCajaEntity egresoCajaEntity =  egresoCajaMapper.domainToEntity(egresoCaja);
        return egresoCajaMapper.entityToDomain(egresoCajaRepository.save(egresoCajaEntity));
    }

    @Override
    public void updateMotivo(Integer id, String motivoAnulacion) throws EgresoCajaNotFoundException {
        EgresoCajaEntity egresoCajaEntity = egresoCajaRepository.findById(id).orElse(null);
        if (egresoCajaEntity == null) {
            EgresoCajaNotFoundException egresoNotFoundException = new EgresoCajaNotFoundException();
            egresoNotFoundException.addParams(id);
            throw egresoNotFoundException;
        }
        egresoCajaRepository.updateStatus(id, motivoAnulacion);
    }

    @Override
    public EgresoCaja findById(Integer id) throws EgresoCajaNotFoundException {
        EgresoCajaEntity egresoCajaEntity = egresoCajaRepository.findById(id)
                .orElseThrow(() -> {
                    EgresoCajaNotFoundException egresoNotFoundException = new EgresoCajaNotFoundException();
                    egresoNotFoundException.addParams(id);
                    return egresoNotFoundException;
                });
        return egresoCajaMapper.entityToDomain(egresoCajaEntity);
    }

    @Override
    public List<EgresoCaja> findEgresosByDateAndUsuarioAndCaja(LocalDate date, BigInteger usuarioId, BigInteger cajaId) {
        return egresoCajaMapper.entitiesToDomains(egresoCajaRepository.findByFechaCreacionAndUserIdAndCajaId(date, usuarioId, cajaId));
    }

    @Override
    public List<EgresoCaja> findAllByDateBetweenAndLibroDiarioFilter(LibroDiarioFilter libroDiarioFilter) {
         List<EgresoCajaEntity> egresoCajas = egresoCajaRepository.findByFechaCreacionBetweenAndUserIdAndCajaId(libroDiarioFilter.getDateOne(), libroDiarioFilter.getDateTwo(), libroDiarioFilter.getIdUsuario(), libroDiarioFilter.getIdCaja());
         return egresoCajaMapper.entitiesToDomains(egresoCajas);
    }
}
