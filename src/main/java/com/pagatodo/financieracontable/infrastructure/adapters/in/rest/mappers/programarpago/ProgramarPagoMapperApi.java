package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.programarpago;

import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.filter.ProgramarPagoFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.ProgramarPagoRQFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ProgramarPagoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.ProgramarPagoUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoFilterResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.ProgramarPagoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProgramarPagoMapperApi {

    @Mapping(target = "egresoCaja.programarPago", ignore = true)
    @Mapping(target = "egresoCaja", ignore = true)
    ProgramarPagoResponse domainToResponse(ProgramarPago programarPago);

    List<ProgramarPagoResponse> domainsToResponses(List<ProgramarPago> programarPagos);

    @Mapping(target = "parametrizacionConcepto.id", source = "parametrizacionConceptoId")
    ProgramarPago requestToDomain(ProgramarPagoRequest programarPagoRequest);

    List<ProgramarPagoFilterResponse> domainsToFilterResponse(List<ProgramarPago> programarPagoList);

    ProgramarPago requestUpdateToDomain(ProgramarPagoUpdateRequest programarPagoUpdateRequest);

    ProgramarPagoFilter requestFilterToDomain(ProgramarPagoRQFilter programarPagoRQFilter);
}