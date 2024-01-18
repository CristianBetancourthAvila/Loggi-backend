package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.ingreso;

import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.vouchers.IngresoVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.IngresoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.IngresoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.IngresoRqVoucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IngresoMapperApi {

    @Mapping(target = "aperturaCaja.id", source = "aperturaCajaId")
    @Mapping(target = "parametrizacionConcepto.id", source = "parametrizacionConceptoId")
    Ingreso requestToDomain(IngresoRequest request);

    IngresoResponse domainToResponse(Ingreso ingreso);

    IngresoVoucher requestVoucherToVoucher(IngresoRqVoucher requestVoucher);

    FileReportResponse domainToResponse(FileReport domain);
}
