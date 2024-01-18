package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.mappers.anulacion;

import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.domain.models.filter.AnulacionFilter;
import com.pagatodo.financieracontable.domain.models.vouchers.AnulacionVoucher;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter.AnulacionRqFilter;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.AnulacionResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.FileReportResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers.AnulacionRqVoucher;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnulacionMapperApi {
    default AnulacionResponse domainToResponse(Anulacion anulacion){
        AnulacionResponse anulacionResponse = new AnulacionResponse();
        if (anulacion == null){
            return null;
        }
        if(anulacion.getTipoMovimiento().equals(TipoMovimiento.INGRESO)){
            Ingreso ingreso = anulacion.getIngreso();
            anulacionResponse.setMovimientoId(ingreso.getId());
            anulacionResponse.setIdentificacion(ingreso.getParametrizacionConcepto().getId());
            anulacionResponse.setCodigoConcepto(ingreso.getParametrizacionConcepto().getCodigoConcepto());
            anulacionResponse.setNombreConcepto(ingreso.getParametrizacionConcepto().getNombreConcepto());
            anulacionResponse.setTipoConcepto(ingreso.getParametrizacionConcepto().getTipoConcepto());
            anulacionResponse.setTitularDocumento(ingreso.getAperturaCaja().getUsuarioId().intValue());
            anulacionResponse.setValor(ingreso.getValorRecibido());
            anulacionResponse.setMotivo(ingreso.getMotivoAnulacion());
        }else if(anulacion.getTipoMovimiento().equals(TipoMovimiento.EGRESO)){
            EgresoCaja egresoCaja = anulacion.getEgresoCaja();
            anulacionResponse.setMovimientoId(egresoCaja.getId());
            anulacionResponse.setIdentificacion(egresoCaja.getProgramarPago().getParametrizacionConcepto().getId());
            anulacionResponse.setCodigoConcepto(egresoCaja.getProgramarPago().getParametrizacionConcepto().getCodigoConcepto());
            anulacionResponse.setNombreConcepto(egresoCaja.getProgramarPago().getParametrizacionConcepto().getNombreConcepto());
            anulacionResponse.setTipoConcepto(egresoCaja.getProgramarPago().getParametrizacionConcepto().getTipoConcepto());
            anulacionResponse.setTitularDocumento(egresoCaja.getUsuarioId());
            anulacionResponse.setValor(egresoCaja.getProgramarPago().getValor());
            anulacionResponse.setMotivo(egresoCaja.getMotivoAnulacion());
        }
        anulacionResponse.setId(anulacion.getId());
        anulacionResponse.setEstadoAnulacion(anulacion.getEstado());
        anulacionResponse.setTipo(anulacion.getTipoMovimiento());
        anulacionResponse.setFechaSolicitud(anulacion.getFechaCreacion());
        anulacionResponse.setHoraSolicitud(anulacion.getHoraCreacion());
        anulacionResponse.setAutorizado(anulacion.getAutorizadorId());
        return anulacionResponse;
    }

    List<AnulacionResponse> domainsToResponses(List<Anulacion> anulacionList);

    AnulacionFilter requestFilterToDomain(AnulacionRqFilter anulacionRqFilter);

    FileReportResponse domainToResponse(FileReport domain);

    AnulacionVoucher requestVoucherToVoucher(AnulacionRqVoucher requestVoucher);
}
