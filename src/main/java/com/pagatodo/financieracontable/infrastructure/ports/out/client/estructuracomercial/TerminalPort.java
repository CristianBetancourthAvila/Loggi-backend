package com.pagatodo.financieracontable.infrastructure.ports.out.client.estructuracomercial;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.client.Terminal;

import java.util.List;

public interface TerminalPort {
    List<Terminal> findByMac(Integer rowsPerPage, Integer skip, Long companyId, String mac) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;
}
