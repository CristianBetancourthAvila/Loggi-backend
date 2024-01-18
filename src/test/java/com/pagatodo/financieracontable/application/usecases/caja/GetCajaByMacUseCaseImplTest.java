package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.CajaInfo;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.client.estructuracomercial.TerminalPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCajaByMacUseCaseImplTest {

    @Mock
    private TerminalPort terminalPort;

    @Mock
    private CajaPort cajaPort;

    @InjectMocks
    private GetCajaByMacUseCaseImpl getCajaByMacUseCase;

    @Test
    @DisplayName("Test findByMac method with existing terminal and caja")
    void findByMac_ExistingTerminalAndCaja_ReturnCajaInfo() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        // Arrange
        String mac = "sampleMac";
        Long companyId = 123L;
        Terminal terminal = new Terminal(/* ... */); // Inicializa el objeto Terminal con valores apropiados
        Caja caja = new Caja(/* ... */); // Inicializa el objeto Caja con valores apropiados

        when(terminalPort.findByMac(anyInt(), anyInt(), anyLong(), eq(mac))).thenReturn(Collections.singletonList(terminal));
        when(cajaPort.findAllByPVTerminalId(anyList())).thenReturn(Collections.singletonList(caja));

        // Act
        CajaInfo result = getCajaByMacUseCase.findByMac(mac, companyId);

        // Assert
        assertNotNull(result);
        // Agrega más aserciones según tus requisitos y la lógica de tu código
    }

    @Test
    @DisplayName("Test findByMac method with non-existing caja")
    void findByMac_NonExistingCaja_ThrowCajaNotFoundException() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        // Arrange
        String mac = "sampleMac";
        Long companyId = 123L;
        Terminal terminal = new Terminal();
        when(terminalPort.findByMac(anyInt(), anyInt(), anyLong(), eq(mac))).thenReturn(Collections.singletonList(terminal));
        when(cajaPort.findAllByPVTerminalId(anyList())).thenReturn(Collections.emptyList()); // Simula la lista vacía de cajas
        assertThrows(CajaNotFoundException.class, () -> getCajaByMacUseCase.findByMac(mac, companyId));
    }
}
