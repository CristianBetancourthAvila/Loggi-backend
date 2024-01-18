package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.parametrizacionconcepto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ParametrizacionConceptoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ConceptoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ParametrizacionConceptoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParametrizacionConceptoMapperApi {
	
    ParametrizacionConcepto requestToDomain(ParametrizacionConceptoRequest request);
    List<ParametrizacionConcepto> requestsToDomains(List<ParametrizacionConceptoRequest> request);

    ParametrizacionConceptoResponse domainToResponse(ParametrizacionConcepto domain);
    List<ParametrizacionConceptoResponse> domainsToResponses(List<ParametrizacionConcepto> domain);
    
    @Mapping(target = "codigo", source = "codigoConcepto")
    @Mapping(target = "nombre", source = "nombreConcepto")
    @Mapping(target = "parametrizacionConceptoId", source = "id")
    ConceptoResponse domainToConceptoResponse(ParametrizacionConcepto domain);
    List<ConceptoResponse> domainsToConceptoResponses(List<ParametrizacionConcepto> domain);
    
    FileReportResponse domainToResponse(FileReport domain);
    
}
