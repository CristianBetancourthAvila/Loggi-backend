package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.conciliacion;

import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ConciliacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConciliacionMapper {

    Conciliacion entityToDomain(ConciliacionEntity conciliacionEntity);

    ConciliacionEntity domainToEntity(Conciliacion conciliacion);

    List<Conciliacion> entitiesToDomains(List<ConciliacionEntity> conciliacionEntities);
}
