package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
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
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCajaByUserIdUseCaseImplTest {

    @Mock
    private TerminalPort terminalPort;

    @Mock
    private CajaPort cajaPort;

    @InjectMocks
    private GetCajaByUserIdUseCaseImpl getCajaByIdUserUseCase;

    @Test
    @DisplayName("Test for findCajaByIdUser method when Cajas list is not empty")
    void findCajaByIdUser_NotEmptyCajasList_ReturnFirstCaja() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        Long idUser = 123L;
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        caja.setNombreCaja("Caja 1");
        List<Caja> cajas = Collections.singletonList(caja);
        Terminal terminal = new Terminal();
        terminal.setId(1L);
        when(terminalPort.findByUserId(idUser)).thenReturn(Collections.singletonList(terminal));
        when(cajaPort.findAllByPVTerminalId(Collections.singletonList(1L))).thenReturn(cajas);
        Caja result = getCajaByIdUserUseCase.findCajaByUserId(idUser);
        assertEquals(caja, result);
        verify(terminalPort, times(1)).findByUserId(idUser);
        verify(cajaPort, times(1)).findAllByPVTerminalId(Collections.singletonList(1L));
    }

    @Test
    @DisplayName("Test for findCajaByIdUser method when Cajas list is empty")
    void findCajaByIdUser_EmptyCajasList_ThrowCajaNotFoundException() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        Long idUser = 123L;
        Terminal terminal = new Terminal();
        terminal.setId(1L);
        when(terminalPort.findByUserId(idUser)).thenReturn(Collections.singletonList(terminal));
        when(cajaPort.findAllByPVTerminalId(Collections.singletonList(1L))).thenReturn(Collections.emptyList());
        assertThrows(CajaNotFoundException.class, () -> getCajaByIdUserUseCase.findCajaByUserId(idUser));
        verify(terminalPort, times(1)).findByUserId(idUser);
        verify(cajaPort, times(1)).findAllByPVTerminalId(Collections.singletonList(1L));
    }
}
