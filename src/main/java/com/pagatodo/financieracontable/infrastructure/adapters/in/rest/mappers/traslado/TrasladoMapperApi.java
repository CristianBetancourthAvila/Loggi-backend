package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.traslado;

import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request.TrasladoRequest;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoFilterByDateResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.TrasladoSendReceiveResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrasladoMapperApi {

    @Mapping(target = "cajaOrigen.id", source = "cajaOrigenId")
    @Mapping(target = "cajaDestino.id", source = "cajaDestinoId")
    Traslado requestToDomain(TrasladoRequest request);

    @Mapping(target = "cajaOrigenId", source = "cajaOrigen.id")
    @Mapping(target = "cajaDestinoId", source = "cajaDestino.id")
    TrasladoResponse domainToResponse(Traslado domain);

    @Mapping(target = "consecutivo", source = "id")
    @Mapping(target = "cajaOrigen", expression = "java(domain.getCajaOrigen().getCodigoCaja() + \" - \" + domain.getCajaOrigen().getNombreCaja())")
    @Mapping(target = "cajaDestino", expression = "java(domain.getCajaDestino().getCodigoCaja() + \" - \" + domain.getCajaDestino().getNombreCaja())")
    TrasladoFilterByDateResponse domainToFilterResponse(Traslado domain);

    List<TrasladoFilterByDateResponse> domainsToFilterResponses(List<Traslado> domains);

    default TrasladoSendReceiveResponse domainToSendReceiveResponse(Traslado domain, Boolean send){
        if ( domain == null ) {
            return null;
        }
        TrasladoSendReceiveResponse response = new TrasladoSendReceiveResponse();
        if(Boolean.TRUE.equals(send) && domain.getEgresoCaja()!=null){
             response.setValor(domain.getEgresoCaja().getProgramarPago().getValor());
             response.setComprobante(domain.getEgresoCaja().getId());
        }else if(Boolean.FALSE.equals(send) && domain.getIngreso()!=null){
             response.setValor(domain.getIngreso().getValorRecibido());
             response.setComprobante(domain.getIngreso().getId());
        }else{
             response.setValor(null);
             response.setComprobante(null);
        }
        response.setEstado(domain.getEstado());
        response.setConsecutivo(domain.getId());
        response.setCajaOrigenCajero(domain.getCajaOrigen().getCodigoCaja() + " - " +domain.getCajaOrigen().getNombreCaja());
        return response;

    }

    default List<TrasladoSendReceiveResponse> domainsToSendReceiveResponses(List<Traslado> domains, Boolean send){
        if ( domains == null ) {
            return Collections.emptyList();
        }

        List<TrasladoSendReceiveResponse> list = new ArrayList<>( domains.size() );
        for ( Traslado traslado : domains ) {
            list.add( domainToSendReceiveResponse( traslado, send ) );
        }

        return list;
    }

}
