package com.pagatodo.financieracontable.infrastructure.ports.in.recaudocartera;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.RecaudoCartera;
import com.pagatodo.financieracontable.domain.models.vouchers.RecaudoCarteraVoucher;
import net.sf.jasperreports.engine.JRException;

public interface RecaudoCarteraUseCase {

    RecaudoCartera create(RecaudoCartera recaudoCartera) throws NotFoundException, BusinessException;

    FileReport generateVoucher(RecaudoCarteraVoucher voucher) throws JRException;
}
