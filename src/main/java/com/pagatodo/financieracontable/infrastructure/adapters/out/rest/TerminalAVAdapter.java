package com.pagatodo.financieracontable.infrastructure.adapters.out.rest;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.commons.rest.FeignClientExecutor;
import com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient.AVV1ApiClientBadRequestException;
import com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient.AVV1ApiClientConnectionException;
import com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient.AVV1ApiClientNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.adminisvendedorv1apiclient.AVV1ApiClientUnknownException;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.AVV1ApiClient;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.mappers.estructuracomercial.TerminalECMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response.TerminalResponse;
import com.pagatodo.financieracontable.infrastructure.ports.out.client.controloperacionadminve.TerminalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TerminalAVAdapter implements TerminalPort {

    private final AVV1ApiClient aVV1ApiClient;

    private final TerminalECMapper terminalECMapper;
    @Override
    public List<Terminal> findByIdSeller(Long idSeller) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        //TODO: Validar propagación de la excepción
        List<TerminalResponse> response = new FeignClientExecutor<>(
                () -> aVV1ApiClient.findByIdSeller(idSeller))
                .throwOnConnectionError(AVV1ApiClientConnectionException::new)
                .throwOnNotFound(AVV1ApiClientNotFoundException::new)
                .throwOnBadRequestError(AVV1ApiClientBadRequestException::new)
                .throwOnUnknownException(AVV1ApiClientUnknownException::new)
                .execute();
        return terminalECMapper.reponsesToDomains(response);
    }

    @Override
    public List<Terminal> findByUserId(Long userId) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        //TODO: Validar propagación de la excepción
        List<TerminalResponse> response = new FeignClientExecutor<>(
                () -> aVV1ApiClient.findByIdUser(userId))
                .throwOnConnectionError(AVV1ApiClientConnectionException::new)
                .throwOnNotFound(AVV1ApiClientNotFoundException::new)
                .throwOnBadRequestError(AVV1ApiClientBadRequestException::new)
                .throwOnUnknownException(AVV1ApiClientUnknownException::new)
                .execute();
        return terminalECMapper.reponsesToDomains(response);
    }
}
