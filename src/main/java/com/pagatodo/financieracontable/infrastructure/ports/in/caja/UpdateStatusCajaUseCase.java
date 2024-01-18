package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import java.math.BigInteger;

public interface UpdateStatusCajaUseCase {
    void updateStatus(BigInteger id) throws CajaIdNotFoundException;
}
