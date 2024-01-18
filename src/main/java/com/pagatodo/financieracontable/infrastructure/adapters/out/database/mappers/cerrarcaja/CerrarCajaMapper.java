package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.cerrarcaja;

import com.pagatodo.financieracontable.domain.models.CerrarCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CerrarCajaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CerrarCajaMapper {

    CerrarCaja entityToDomain(CerrarCajaEntity entity);

    CerrarCajaEntity domainToEntity(CerrarCaja domain);


}
