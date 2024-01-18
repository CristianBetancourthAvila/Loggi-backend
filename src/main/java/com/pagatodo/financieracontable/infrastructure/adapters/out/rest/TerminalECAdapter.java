package com.pagatodo.financieracontable.infrastructure.adapters.out.rest;
import com.pagatodo.commons.exceptions.ApiErrorCode;
import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.commons.rest.FeignClientExecutor;
import com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientBadRequestException;
import com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientConnectionException;
import com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.estructuracomercialv1apiclient.ECV1ApiClientUnknownException;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.ECApiClient;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.mappers.estructuracomercial.TerminalECMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response.TerminalResponse;
import com.pagatodo.financieracontable.infrastructure.ports.out.client.estructuracomercial.TerminalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TerminalECAdapter implements TerminalPort {

    private final ECApiClient ecApiClient;

    private final TerminalECMapper terminalECMapper;

    private static final ApiErrorCode TERMINAL_NOT_FOUND = ECV1ApiClientErrorCodes.ECV1_TERMINAL_NOT_FOUND;

    @Override
    public List<Terminal> findByMac(Integer rowsPerPage, Integer skip, Long companyId, String mac) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        //TODO: Validar propagación de la excepción
        PageResponse<List<TerminalResponse>> response = new FeignClientExecutor<>(
                () -> ecApiClient.findByMac(rowsPerPage, skip, companyId, mac))
                .throwOnConnectionError(ECV1ApiClientConnectionException::new)
                .throwOnNotFound(ECV1ApiClientNotFoundException::new)
                .throwOnBadRequestError(ECV1ApiClientBadRequestException::new)
                .throwOnUnknownException(ECV1ApiClientUnknownException::new)
                .execute();
        if (Objects.equals(response.getTotal(), BigInteger.ZERO)){
            throw new ECV1ApiClientNotFoundException(TERMINAL_NOT_FOUND);
        }
        return terminalECMapper.reponsesToDomains(response.getData());
    }
}
