package com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TerminalResponseTest {

    @Test
    @DisplayName("Test for TerminalResponse model")
    void testModelTerminalResponse() {
        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setId(1L);
        terminalResponse.setName("Terminal 1");
        terminalResponse.setBrand("Brand 1");
        terminalResponse.setIdTeam("Team ID 1");
        terminalResponse.setTypeTeamName("Type Team 1");
        terminalResponse.setState("Active");
        terminalResponse.setPointSale("Point Sale 1");
        terminalResponse.setIdPointSale(100L);
        terminalResponse.setTypeTeam(200L);
        terminalResponse.setSerial("Serial 123");
        terminalResponse.setZone(50L);

        assertDoesNotThrow(() -> PropertyTester.test(terminalResponse));
    }
}
