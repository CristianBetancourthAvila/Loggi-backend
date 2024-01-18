package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import java.math.BigInteger;

public interface GetCajaByIdUseCase {
    Caja findById(BigInteger id);
}
