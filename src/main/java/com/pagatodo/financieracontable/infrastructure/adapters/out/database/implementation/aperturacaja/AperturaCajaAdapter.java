package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.aperturacaja;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AperturaCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.aperturacaja.AperturaCajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.AperturaCajaRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;


@Component
@RequiredArgsConstructor
public class AperturaCajaAdapter implements AperturaCajaPort {

    private final AperturaCajaMapper aperturaCajaMapper;

    private final AperturaCajaRepository aperturaCajaRepository;

    @Override
    public AperturaCaja create(AperturaCaja aperturaCaja) {

        AperturaCajaEntity aperturaCajaEntity = aperturaCajaMapper.domainToEntity(aperturaCaja);
        AperturaCajaEntity aperturaCajaSaved = aperturaCajaRepository.save(aperturaCajaEntity);

        return aperturaCajaMapper.entityToDomain(aperturaCajaSaved);
    }

    @Override
    public AperturaCaja getLastRecord(BigInteger cajaId) {
        AperturaCajaEntity aperturaCajaEntity = aperturaCajaRepository.getLastRecord(cajaId);
        return aperturaCajaMapper.entityToDomain(aperturaCajaEntity);
    }

    @Override
    public AperturaCaja findById(Long id) {
        return aperturaCajaMapper.entityToDomain(aperturaCajaRepository.findById(id).orElse(null));
    }

    @Override
    public void updateStatus(Long id, boolean status) {
        aperturaCajaRepository.updateStatus(id, status);
    }
}
