package com.pagatodo.financieracontable.infrastructure.ports.in.programarpago;

import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;

import java.math.BigInteger;
import java.util.List;

public interface UpdatePPUseCase {
    ProgramarPago update(ProgramarPago programarPago, List<BigInteger> cajaIds) throws ProgramarPagoNotFoundException, CajaNotFoundException, AperturaCajaNotFoundException, CajaIdNotFoundException;
}
