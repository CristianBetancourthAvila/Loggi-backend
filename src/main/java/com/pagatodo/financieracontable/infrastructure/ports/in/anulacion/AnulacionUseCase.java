package com.pagatodo.financieracontable.infrastructure.ports.in.anulacion;

import com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionDataNotFounException;
import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.filter.AnulacionFilter;
import com.pagatodo.financieracontable.domain.models.vouchers.AnulacionVoucher;
import com.pagatodo.financieracontable.domain.records.PageModel;
import net.sf.jasperreports.engine.JRException;


import java.util.List;

public interface AnulacionUseCase {

   PageModel<List<Anulacion>> findAnulacionesByCriteria(AnulacionFilter anulacionFilter, Integer rowsPerPage, Integer skip);

   void updateAuthorizerUser(Integer id, Integer userId) throws AnulacionDataNotFounException;

   FileReport generateVoucher(AnulacionVoucher voucher) throws JRException;
}
