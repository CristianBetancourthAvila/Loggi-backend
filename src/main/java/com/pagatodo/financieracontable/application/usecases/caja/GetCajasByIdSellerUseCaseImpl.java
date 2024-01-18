package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajasByIdSellerUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.client.controloperacionadminve.TerminalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCajasByIdSellerUseCaseImpl implements GetCajasByIdSellerUseCase {

    private final TerminalPort terminalPort;
    private final CajaPort cajaPort;

    @Override
    public List<Caja> findCajasByIdSeller(Long idSeller) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        List<Long> terminalIds = terminalPort.findByIdSeller(idSeller)
                .stream()
                .map(Terminal::getId)
                .toList();
        return cajaPort.findAllByPVTerminalId(terminalIds);
    }
}
