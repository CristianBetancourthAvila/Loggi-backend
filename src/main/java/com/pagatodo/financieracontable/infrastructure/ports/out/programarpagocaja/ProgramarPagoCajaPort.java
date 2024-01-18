package com.pagatodo.financieracontable.infrastructure.ports.out.programarpagocaja;

import com.pagatodo.financieracontable.domain.models.ProgramarPagoCaja;
import java.math.BigInteger;

public interface ProgramarPagoCajaPort {
    ProgramarPagoCaja save(ProgramarPagoCaja programarPagoCaja);

    ProgramarPagoCaja findPPByProgramarPagoIdAndCajaId(Integer programarPagoId, BigInteger cajaId);

    void delete(ProgramarPagoCaja programarPagoCaja);
}
