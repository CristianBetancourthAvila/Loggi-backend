package com.pagatodo.financieracontable.infrastructure.ports.out.client.controloperacionadminve;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import java.util.List;

public interface TerminalPort {
    List<Terminal> findByIdSeller(Long idSeller) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;

    List<Terminal> findByUserId(Long userId) throws NotFoundException, BadRequestException, UnknownException, ConnectionException;
}
