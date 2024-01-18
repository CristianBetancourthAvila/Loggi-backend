package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.recaudocartera;


import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.RecaudoCartera;
import com.pagatodo.financieracontable.domain.models.vouchers.RecaudoCarteraVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.RecaudoCarteraRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.RecaudoCarteraResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.RecaudoCarteraRqVoucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecaudoCarteraMapperApi {

    @Mapping(target = "cartera.id", source = "carteraId")
    @Mapping(target = "aperturaCaja.id", source = "aperturaCajaId")
    RecaudoCartera requestToDomain(RecaudoCarteraRequest request);

    RecaudoCarteraResponse domainToResponse(RecaudoCartera domain);

    FileReportResponse domainToResponse(FileReport domain);

    RecaudoCarteraVoucher requestVoucherToVoucher(RecaudoCarteraRqVoucher requestVoucher);

}
