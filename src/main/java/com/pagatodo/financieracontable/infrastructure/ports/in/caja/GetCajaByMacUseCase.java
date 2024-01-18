package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.CajaInfo;

public interface GetCajaByMacUseCase {

    CajaInfo findByMac(String mac, Long companyId) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;
}
