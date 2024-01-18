package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.cerrarcaja;

import com.pagatodo.financieracontable.domain.models.CerrarCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CerrarCajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.cerrarcaja.CerrarCajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.CerrarCajaRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.cerrarcaja.CerrarCajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CerrarCajaAdapter implements CerrarCajaPort {

    private final CerrarCajaMapper cerrarCajaMapper;

    private final CerrarCajaRepository cerrarCajaRepository;
    @Override
    public CerrarCaja create(CerrarCaja cerrarCaja) {
        CerrarCajaEntity entity = cerrarCajaMapper.domainToEntity(cerrarCaja);
        CerrarCajaEntity savedCerrarCaja = cerrarCajaRepository.save(entity);

        return cerrarCajaMapper.entityToDomain(savedCerrarCaja);
    }
}
