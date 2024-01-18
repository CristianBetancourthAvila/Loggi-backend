package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.recaudocartera;

import com.pagatodo.financieracontable.domain.models.RecaudoCartera;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.RecaudoCarteraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecaudoCarteraMapper {
    RecaudoCarteraEntity domainToEntity(RecaudoCartera domain);

    RecaudoCartera entityToDomain(RecaudoCarteraEntity entity);
}
