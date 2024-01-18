package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.UpdateStatusCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class UpdateStatusCajaUseCaseImpl implements UpdateStatusCajaUseCase {

    private final CajaPort cajaPort;

    @Transactional
    @Override
    public void updateStatus(BigInteger id) throws CajaIdNotFoundException {
        Caja cajaSaved = cajaPort.findById(id);
        if (cajaSaved == null) {
            throw new CajaIdNotFoundException();
        }
        Estado newStatus = (cajaSaved.getEstado() == Estado.ACTIVO) ? Estado.INACTIVO : Estado.ACTIVO;
        cajaPort.updateStatus(id, newStatus);
    }
}
