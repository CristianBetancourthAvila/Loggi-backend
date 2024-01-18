package com.pagatodo.financieracontable.infrastructure.ports.in.programarpago;

import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;

import java.math.BigInteger;
import java.util.List;

public interface CreatePPUseCase {
    ProgramarPago create(ProgramarPago programarPago, List<BigInteger> cajaIds) throws AperturaCajaBusinessException, AperturaCajaNotFoundException, CajaIdNotFoundException;
}
