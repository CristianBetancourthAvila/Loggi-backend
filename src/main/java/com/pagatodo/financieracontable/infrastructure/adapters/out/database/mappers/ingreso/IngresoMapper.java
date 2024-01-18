package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.ingreso;

import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.IngresoEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngresoMapper {

    @Mapping(target = "parametrizacionConcepto.programarPago.parametrizacionConcepto", ignore = true)
    @Mapping(target = "parametrizacionConcepto.programarPago.egresoCaja.programarPago", ignore = true)
    Ingreso entityToDomain(IngresoEntity entity);

    IngresoEntity domainToEntity(Ingreso ingreso);

    List<Ingreso> entitiesToDomains(List<IngresoEntity> ingresoEntities);
}
