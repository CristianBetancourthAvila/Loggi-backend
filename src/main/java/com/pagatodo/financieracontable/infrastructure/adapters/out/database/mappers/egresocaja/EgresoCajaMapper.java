package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.egresocaja;

import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.EgresoCajaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EgresoCajaMapper {

    @Mapping(target = "programarPago.parametrizacionConcepto.programarPago", ignore = true)
    @Mapping(target = "programarPago.egresoCaja.programarPago", ignore = true)
    EgresoCaja entityToDomain(EgresoCajaEntity egresoCaja);

    EgresoCajaEntity domainToEntity(EgresoCaja egresoCaja);

    List<EgresoCaja> entitiesToDomains(List<EgresoCajaEntity> egresoCajaEntityList);
}
