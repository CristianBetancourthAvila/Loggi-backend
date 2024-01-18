package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.recaudocartera;

import com.pagatodo.financieracontable.domain.models.RecaudoCartera;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.RecaudoCarteraEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.recaudocartera.RecaudoCarteraMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.RecaudoCarteraRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.recaudocartera.RecaudoCarteraPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecaudoCarteraAdapter implements RecaudoCarteraPort {

    private final RecaudoCarteraRepository recaudoCarteraRepository;

    private final RecaudoCarteraMapper recaudoCarteraMapper;
    @Override
    public RecaudoCartera save(RecaudoCartera recaudoCartera) {
        RecaudoCarteraEntity recaudoCarteraEntity = recaudoCarteraMapper.domainToEntity(recaudoCartera);
        return recaudoCarteraMapper.entityToDomain(recaudoCarteraRepository.save(recaudoCarteraEntity));
    }
}
