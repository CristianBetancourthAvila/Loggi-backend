package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.librodiario;

import com.pagatodo.financieracontable.domain.models.LibroDiario;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.LibroDiarioRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.LibroDiarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibroDiarioMapperApi {
    LibroDiarioFilter requestToDomain(LibroDiarioRqFilter libroDiarioRqFilter);

    List<LibroDiarioResponse> domainsToResponses(List<LibroDiario> libroDiario);

}
