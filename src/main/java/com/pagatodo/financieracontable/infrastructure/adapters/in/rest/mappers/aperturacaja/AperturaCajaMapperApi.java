package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.aperturacaja;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.AperturaCajaRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.AperturaCajaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AperturaCajaMapperApi {
     @Mapping(target = "caja.id", source = "cajaId")
     AperturaCaja requestToDomain(AperturaCajaRequest request);

     @Mapping(target = "nombreCaja", expression = "java(showData ? caja.getNombreCaja(): null)")
     @Mapping(target = "codigoCaja", expression = "java(showData ? caja.getCodigoCaja(): null)")
     @Mapping(target = "abierta", source = "status")
     @Mapping(target = "aperturaCajaId", expression = "java(showData && aperturaCaja!=null && status ? aperturaCaja.getId(): null)")
     @Mapping(target = "fechaCreacion", expression = "java(showData && aperturaCaja!=null && status ? aperturaCaja.getFechaApertura(): null)")
     @Mapping(target = "horaCreacion", expression = "java(showData && aperturaCaja!=null && status ? aperturaCaja.getHoraApertura(): null)")
     @Mapping(target = "saldoInicial", expression = "java(showData ? saldoInicial: null)")
     AperturaCajaResponse domainToResponse(Caja caja, boolean status, AperturaCaja aperturaCaja, boolean showData, Long saldoInicial);
}
