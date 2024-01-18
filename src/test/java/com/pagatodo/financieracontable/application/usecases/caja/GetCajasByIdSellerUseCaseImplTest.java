package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.client.controloperacionadminve.TerminalPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCajasByIdSellerUseCaseImplTest {

    @Mock
    private TerminalPort terminalPort;

    @Mock
    private CajaPort cajaPort;

    @InjectMocks
    private GetCajasByIdSellerUseCaseImpl getCajasByIdSeller;

    @Test
    @DisplayName("Test for findCajasByIdSeller method")
    void findCajasByIdSeller_ValidId_ReturnCajas() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        Long idSeller = 123L;
        Terminal terminal = new Terminal();
        terminal.setId(1L);
        terminal.setName("Terminal 1");
        List<Terminal> terminals = List.of(terminal);
        when(terminalPort.findByIdSeller(idSeller)).thenReturn(terminals);
        List<Long> terminalIds = List.of(1L);
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        caja.setNombreCaja("Caja 1");
        List<Caja> cajas = List.of(caja);
        when(cajaPort.findAllByPVTerminalId(terminalIds)).thenReturn(cajas);
        List<Caja> result = getCajasByIdSeller.findCajasByIdSeller(idSeller);
        assertEquals(cajas, result);
        verify(terminalPort, times(1)).findByIdSeller(idSeller);
        verify(cajaPort, times(1)).findAllByPVTerminalId(terminalIds);
    }
}
