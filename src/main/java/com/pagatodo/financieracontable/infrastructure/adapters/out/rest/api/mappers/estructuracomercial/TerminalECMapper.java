package com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.mappers.estructuracomercial;

import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response.TerminalResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TerminalECMapper {
    List<Terminal> reponsesToDomains(List<TerminalResponse> terminalResponseList);
}
