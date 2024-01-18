package com.pagatodo.financieracontable.application.usecases.anulacion;

import com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionDataNotFounException;
import com.pagatodo.financieracontable.application.usecases.commons.GenerateFileUtils;
import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.filter.AnulacionFilter;
import com.pagatodo.financieracontable.domain.models.vouchers.AnulacionVoucher;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.in.anulacion.AnulacionUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.anulacion.AnulacionPort;
import net.sf.jasperreports.engine.JRException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnulacionUseCaseImpl implements AnulacionUseCase {

    private final AnulacionPort anulacionPort;
    private static final String REPORT_TEMPLATE_NAME= "anulacion_comprobante_pdf.jrxml";
    private static final String BASE_TEMPLATE_TITLE= "anulacion-comprobante";
    @Transactional(readOnly = true)
    @Override
    public PageModel<List<Anulacion>> findAnulacionesByCriteria(AnulacionFilter anulacionFilter, Integer rowsPerPage, Integer skip) {
           return anulacionPort.findAnulacionesByCriteria(anulacionFilter.getTipoMovimiento(), anulacionFilter.getFechaCreacion(), anulacionFilter.getEstado(), rowsPerPage, skip);
    }

    @Transactional
    @Override
    public void updateAuthorizerUser(Integer id, Integer userId) throws AnulacionDataNotFounException {
        Anulacion anulacion = anulacionPort.findById(id);
        if(anulacion == null){
            AnulacionDataNotFounException errorNotFound = new AnulacionDataNotFounException();
            errorNotFound.addParams(id);
            throw errorNotFound;
        }
        anulacionPort.updateAuthorizerUser(id, userId, EstadoAnulacion.ANULADO);
    }

    @Override
    public FileReport generateVoucher(AnulacionVoucher voucher) throws JRException {
        HashMap<String, Object> parameters = new HashMap<>();
        String finalName = BASE_TEMPLATE_TITLE + "-" + voucher.getComprobante();
        parameters.put("comprobante", voucher.getComprobante()  != null ? voucher.getComprobante(): "");
        parameters.put("fechaHora", voucher.getFechaHora()  != null ? voucher.getFechaHora(): "");
        parameters.put("recibido", voucher.getRecibido()  != null ? voucher.getRecibido().toUpperCase(): "" );
        parameters.put("tipoDocumento", voucher.getTipoDocumento()  != null ? voucher.getTipoDocumento(): "");
        parameters.put("numeroDocumento", voucher.getNumeroDocumento()  != null ? voucher.getNumeroDocumento(): "");
        parameters.put("valor", voucher.getValor()  != null ? voucher.getValor(): "" );
        parameters.put("concepto", voucher.getConcepto()  != null ? voucher.getConcepto(): "");
        parameters.put("fechaSolicitud", voucher.getFechaSolicitud() != null ? voucher.getFechaSolicitud(): "");
        parameters.put("comprobanteMovimiento", voucher.getComprobanteMovimiento() != null ? voucher.getComprobanteMovimiento(): "");
        parameters.put("autorizado", voucher.getAutorizado() != null ? voucher.getAutorizado():"");
        parameters.put("motivo", voucher.getMotivo() != null ? voucher.getMotivo(): "");
        parameters.put("autorizador", voucher.getAutorizador() != null ? voucher.getAutorizador(): "");
        parameters.put("identificacion", voucher.getIdentificacion() != null ? voucher.getIdentificacion(): "");
        return GenerateFileUtils.exportFileAsPDF(REPORT_TEMPLATE_NAME,parameters,finalName, null);
    }
}
