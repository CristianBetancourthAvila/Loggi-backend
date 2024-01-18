package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.traslado;

import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.TrasladoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrasladoMapper {
    @Mapping(target = "ingreso.parametrizacionConcepto.programarPago.parametrizacionConcepto", ignore = true)
    @Mapping(target = "ingreso.parametrizacionConcepto.programarPago.egresoCaja.programarPago", ignore = true)
    @Mapping(target = "egresoCaja.programarPago.parametrizacionConcepto.programarPago", ignore = true)
    @Mapping(target = "egresoCaja.programarPago.egresoCaja", ignore = true)
    Traslado entityToDomain(TrasladoEntity entity);

    TrasladoEntity domainToEntity(Traslado domain);

    List<Traslado> entitiesToDomains(List<TrasladoEntity> entities);
}
