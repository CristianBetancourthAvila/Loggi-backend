package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByUserIdUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.client.controloperacionadminve.TerminalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCajaByUserIdUseCaseImpl implements GetCajaByUserIdUseCase {

    private final TerminalPort terminalPort;
    private final CajaPort cajaPort;

    @Transactional(readOnly = true)
    @Override
    public Caja findCajaByUserId(Long userId) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        List<Long> terminalIds = terminalPort.findByUserId(userId)
                .stream()
                .map(Terminal::getId)
                .toList();
        List<Caja> cajas = cajaPort.findAllByPVTerminalId(terminalIds);
        if (!cajas.isEmpty()) {
            return cajas.get(0);
        }
        throw new CajaNotFoundException();
    }
}
