package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.egresocaja;

import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.EgresoCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.EgresoCajaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EgresoCajaMapperApi {

    @Mapping(target = "programarPago.id", source = "programarPagoId")
    @Mapping(target = "aperturaCaja.id", source = "cajaId")
    EgresoCaja requestToDomain(EgresoCajaRequest egresoCajaRequest);

    @Mapping(target = "programarPago", ignore = true)
    EgresoCajaResponse domainToResponse(EgresoCaja egresoCaja);

    List<EgresoCajaResponse> domainsToResponses(List<EgresoCaja> egresoCajaList);
}
