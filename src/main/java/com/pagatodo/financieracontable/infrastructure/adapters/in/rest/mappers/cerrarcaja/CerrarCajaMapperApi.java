package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.cerrarcaja;


import com.pagatodo.financieracontable.domain.models.CerrarCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CerrarCajaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CerrarCajaMapperApi {
    @Mapping(target = "aperturaCaja.id", source ="aperturaCajaId" )
    CerrarCaja requestToDomain(CerrarCajaRequest request);
}
