package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.cartera;

import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CarteraRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.CarteraUpdateRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.CarteraResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarteraMapperApi {
    Cartera requestToDomain(CarteraRequest request);
    CarteraResponse domainToResponse(Cartera domain);

    @Mapping(target = "saldo", source = "diferenciaExcedenteSaldo")
    @Mapping(target = "ventaDiaLiquidada", source = "diferenciaExcedenteVentaDiaLiquidada")
    @Mapping(target = "ventasDia", source = "diferenciaExcedenteVentasDia")
    Cartera updateRequestToDomain(CarteraUpdateRequest request);

}
