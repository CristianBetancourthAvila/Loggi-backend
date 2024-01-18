package com.pagatodo.financieracontable.infrastructure.ports.in.aperturacaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;

import java.math.BigInteger;

public interface AperturaCajaUseCase {

    AperturaCaja create(AperturaCaja aperturaCaja) throws BusinessException, NotFoundException;

    Boolean validateStatus(BigInteger cajaId) throws BusinessException, NotFoundException;

    Caja findAssignedCajaByCajaId(BigInteger sellerId);

    AperturaCaja findLastRecord(BigInteger cajaId);

    void updateStatus(Long id) throws NotFoundException;
}
