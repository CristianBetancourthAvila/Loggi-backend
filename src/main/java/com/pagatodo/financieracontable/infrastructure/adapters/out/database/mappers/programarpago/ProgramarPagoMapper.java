package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.programarpago;

import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProgramarPagoMapper {

    @Mapping(target = "parametrizacionConcepto.programarPago", ignore = true)
    @Mapping(target = "egresoCaja.programarPago", ignore = true)
    ProgramarPago entityToDomain(ProgramarPagoEntity programarPagoEntity);

    ProgramarPagoEntity domainToEntity(ProgramarPago programarPago);

    List<ProgramarPago> entitiesToDomains(List<ProgramarPagoEntity> programarPagoEntities);

    void mergeToEntity(@MappingTarget ProgramarPagoEntity target, ProgramarPago source);
}
