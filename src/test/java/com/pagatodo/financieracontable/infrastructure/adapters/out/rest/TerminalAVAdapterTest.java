package com.pagatodo.financieracontable.infrastructure.adapters.out.rest;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.AVV1ApiClient;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.mappers.estructuracomercial.TerminalECMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response.TerminalResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TerminalAVAdapterTest {

    @Mock
    private AVV1ApiClient aVV1ApiClient;

    @Mock
    private TerminalECMapper terminalECMapper;

    @InjectMocks
    private TerminalAVAdapter terminalAVAdapter;

    @BeforeEach
    void setUp() {
        terminalAVAdapter = new TerminalAVAdapter(aVV1ApiClient, terminalECMapper);
    }

    @Test
    @DisplayName("Test for findByIdSeller method")
    void findByIdSeller_ReturnTerminalList() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        Long idSeller = 1L;
        List<TerminalResponse> responseList = Collections.singletonList(new TerminalResponse());
        List<Terminal> expectedTerminals = Collections.singletonList(new Terminal());
        when(aVV1ApiClient.findByIdSeller(1L)).thenReturn(ResponseEntity.ok(responseList));
        when(terminalECMapper.reponsesToDomains(responseList)).thenReturn(expectedTerminals);
        List<Terminal> result = terminalAVAdapter.findByIdSeller(idSeller);
        assertEquals(expectedTerminals, result);
        verify(aVV1ApiClient, times(1)).findByIdSeller(anyLong());
        verify(terminalECMapper, times(1)).reponsesToDomains(responseList);
    }

    @Test
    @DisplayName("Test for findByIdUser method")
    void findByIdUser_ReturnTerminalList() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        Long idUser = 1L;
        List<TerminalResponse> responseList = Collections.singletonList(new TerminalResponse());
        List<Terminal> expectedTerminals = Collections.singletonList(new Terminal());
        when(aVV1ApiClient.findByIdUser(1L)).thenReturn(ResponseEntity.ok(responseList));
        when(terminalECMapper.reponsesToDomains(responseList)).thenReturn(expectedTerminals);
        List<Terminal> result = terminalAVAdapter.findByUserId(idUser);
        assertEquals(expectedTerminals, result);
        verify(aVV1ApiClient, times(1)).findByIdUser(anyLong());
        verify(terminalECMapper, times(1)).reponsesToDomains(responseList);
    }
}
