package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.aperturacaja;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AperturaCajaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AperturaCajaMapper {

    AperturaCaja entityToDomain(AperturaCajaEntity entity);

    AperturaCajaEntity domainToEntity(AperturaCaja domain);
}
