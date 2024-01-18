package com.pagatodo.financieracontable.infrastructure.adapters.out.rest;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientNotFoundException;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.ECApiClient;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.mappers.estructuracomercial.TerminalECMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response.TerminalResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TerminalECAdapterTest {

    @Mock
    private ECApiClient ecApiClient;

    @Mock
    private TerminalECMapper terminalECMapper;

    @InjectMocks
    private TerminalECAdapter terminalECAdapter;

    @BeforeEach
    void setUp() {
        terminalECAdapter = new TerminalECAdapter(ecApiClient, terminalECMapper);
    }

    @Test
    @DisplayName("Test for findByMac method - Success")
    void findByMac_Success_ReturnTerminalList() throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        Integer rowsPerPage = 10;
        Integer skip = 0;
        Long companyId = 1L;
        String mac = "sampleMac";
        List<TerminalResponse> responseList = Collections.singletonList(new TerminalResponse());
        List<Terminal> expectedTerminals = Collections.singletonList(new Terminal());
        PageResponse<List<TerminalResponse>> response = new PageResponse<>();
        response.setTotal(BigInteger.ONE);
        response.setData(responseList);
        ResponseEntity<PageResponse<List<TerminalResponse>>> responseEntity = new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        when(ecApiClient.findByMac(rowsPerPage, skip, companyId, mac)).thenReturn(responseEntity);
        when(terminalECMapper.reponsesToDomains(responseList)).thenReturn(expectedTerminals);
        List<Terminal> result = terminalECAdapter.findByMac(rowsPerPage, skip, companyId, mac);
        assertEquals(expectedTerminals, result);
        verify(ecApiClient, times(1)).findByMac(anyInt(), anyInt(), anyLong(), anyString());
        verify(terminalECMapper, times(1)).reponsesToDomains(responseList);
    }

    @Test
    @DisplayName("Test for findByMac method - NotFoundException")
    void findByMac_NotFoundException() {
        Integer rowsPerPage = 10;
        Integer skip = 0;
        Long companyId = 1L;
        String mac = "sampleMac";
        PageResponse<List<TerminalResponse>> response = new PageResponse<>();
        response.setTotal(BigInteger.ZERO);
        ResponseEntity<PageResponse<List<TerminalResponse>>> responseEntity = new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
        when(ecApiClient.findByMac(rowsPerPage, skip, companyId, mac)).thenReturn(responseEntity);
        assertThrows(ECV1ApiClientNotFoundException.class, () -> terminalECAdapter.findByMac(rowsPerPage, skip, companyId, mac));
        verify(ecApiClient, times(1)).findByMac(anyInt(), anyInt(), anyLong(), anyString());
        verifyNoInteractions(terminalECMapper);
    }
}
