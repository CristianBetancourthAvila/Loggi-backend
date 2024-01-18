package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.anulacion;

import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AnulacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnulacionMapper {

    @Mapping(target = "egresoCaja.programarPago.parametrizacionConcepto.programarPago", ignore = true)
    @Mapping(target = "egresoCaja.programarPago.egresoCaja", ignore = true)
    @Mapping(target = "ingreso.parametrizacionConcepto.programarPago.egresoCaja",ignore = true)
    @Mapping(target = "ingreso.parametrizacionConcepto.programarPago.parametrizacionConcepto", ignore = true)
    Anulacion entityToDomain(AnulacionEntity entity);

    AnulacionEntity domainToEntity(Anulacion domain);

    List<Anulacion> entitiesToDomains(List<AnulacionEntity> anulacionList);

}
