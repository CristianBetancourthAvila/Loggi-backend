package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.programarpagocaja;

import com.pagatodo.financieracontable.domain.models.ProgramarPagoCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoCajaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProgramarPagoCajaMapper {

    @Mapping(target = "programarPago.parametrizacionConcepto", ignore = true)
    @Mapping(target = "programarPago.egresoCaja", ignore = true)
    ProgramarPagoCaja entityToDomain(ProgramarPagoCajaEntity programarPagoCaja);

    ProgramarPagoCajaEntity domainToEntity(ProgramarPagoCaja programarPagoCaja);

    List<ProgramarPagoCaja> entitiesToDomains(List<ProgramarPagoCajaEntity> programarPagoCajaList);

}
