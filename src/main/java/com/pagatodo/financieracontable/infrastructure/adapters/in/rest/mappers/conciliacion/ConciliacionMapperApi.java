package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.conciliacion;

import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.ConciliacionRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ConciliacionRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConciliacionFilterResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConciliacionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConciliacionMapperApi {

    Conciliacion requestToDomain(ConciliacionRequest conciliacionRequest);

    ConciliacionResponse domainToResponse(Conciliacion conciliacion);

    ConciliacionFilter requestFilterToDomain(ConciliacionRqFilter conciliacionRqFilter);

    List<ConciliacionFilterResponse> domainsToFilterResponse(List<Conciliacion> conciliacions);
}
