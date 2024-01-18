package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.commons.exceptions.BadRequestException;
import com.pagatodo.commons.exceptions.ConnectionException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.exceptions.UnknownException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.CajaInfo;
import com.pagatodo.financieracontable.domain.models.client.Terminal;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByMacUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.client.estructuracomercial.TerminalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCajaByMacUseCaseImpl implements GetCajaByMacUseCase {

    private final TerminalPort terminalPort;
    private final CajaPort cajaPort;

    @Transactional(readOnly = true)
    @Override
    public CajaInfo findByMac(String mac, Long companyId) throws NotFoundException, BadRequestException, UnknownException, ConnectionException {
        List<Terminal> terminals = terminalPort.findByMac(10,0,companyId, mac);
        List<Caja> cajaList = cajaPort.findAllByPVTerminalId(Collections.singletonList(terminals.get(0).getId()));
        if (!cajaList.isEmpty()) {
            CajaInfo cajaInfo = new CajaInfo();
            cajaInfo.setId(cajaList.get(0).getId());
            cajaInfo.setCodigoCaja(cajaList.get(0).getCodigoCaja());
            cajaInfo.setNombreCaja(cajaList.get(0).getNombreCaja());
            cajaInfo.setSerial(terminals.get(0).getSerial());
            return cajaInfo;
        }
        throw new CajaNotFoundException();
    }
}
