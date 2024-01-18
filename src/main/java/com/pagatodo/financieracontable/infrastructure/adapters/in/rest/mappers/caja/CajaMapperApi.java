package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.CajaInfo;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.filter.CajaFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.CajaRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CajaUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaInfoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaMatchResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CajaResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CajaMapperApi {

    Caja requestToDomain(CajaUpdateRequest cajaUpdateRequest);

    CajaResponse domainToResponse(Caja caja);

    List<Caja> requestsToDomains(List<CajaRequest> cajaRequestList);

    List<CajaResponse> domainsToResponses(List<Caja> cajaList);

    List<CajaMatchResponse> domainsToMatchResponses(List<Caja> cajaList);

    CajaFilter requestFilterToDomain(CajaRqFilter cajaRqFilter);

    FileReportResponse fileDomainToResponse(FileReport fileReport);

    CajaInfoResponse domainToCajaInfoResponse(CajaInfo cajaInfo);
}
