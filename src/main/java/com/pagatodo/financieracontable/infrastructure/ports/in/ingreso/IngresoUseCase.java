package com.pagatodo.financieracontable.infrastructure.ports.in.ingreso;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.vouchers.IngresoVoucher;
import net.sf.jasperreports.engine.JRException;

public interface IngresoUseCase {

    Ingreso create(Ingreso ingreso) throws BusinessException, NotFoundException;

    void updateCancellationReason(Integer id, String cancellationReason) throws BusinessException, NotFoundException;

    FileReport generateVoucher(IngresoVoucher voucher) throws JRException;


}
