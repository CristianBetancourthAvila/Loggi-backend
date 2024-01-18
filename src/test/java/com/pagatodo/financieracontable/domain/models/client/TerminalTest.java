package com.pagatodo.financieracontable.domain.models.client;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TerminalTest {

    @Test
    @DisplayName("Test for Terminal model")
    void testModelTerminal() {
        Terminal terminal = new Terminal();
        terminal.setId(1L);
        terminal.setName("Terminal 1");
        terminal.setBrand("Brand 1");
        terminal.setIdTeam("Team ID 1");
        terminal.setTypeTeamName("Type Team 1");
        terminal.setState("Active");
        terminal.setPointSale("Point Sale 1");
        terminal.setIdPointSale(100L);
        terminal.setTypeTeam(200L);
        terminal.setSerial("Serial 123");
        terminal.setZone(50L);

        assertDoesNotThrow(() -> PropertyTester.test(terminal));
    }
}
