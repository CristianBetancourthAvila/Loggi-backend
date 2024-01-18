package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.CajaProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CajaMapper {

    Caja entityToDomain(CajaEntity entity);

    CajaEntity domainToEntity(Caja domain);

    List<CajaEntity> domainsToEntities(List<Caja> cajaList);

    List<Caja> entitiesToDomains(List<CajaEntity> cajaList);

    List<Caja> cajaProjectionsToDomains(List<CajaProjection> cajaProjections);

    void mergeToEntity(@MappingTarget CajaEntity target, Caja source);
}
