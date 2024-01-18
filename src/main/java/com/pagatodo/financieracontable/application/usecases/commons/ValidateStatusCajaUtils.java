package com.pagatodo.financieracontable.application.usecases.commons;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@RequiredArgsConstructor
@Component
public class ValidateStatusCajaUtils {

    private final CajaPort cajaPort;

    private final AperturaCajaPort aperturaCajaPort;


    public boolean validateCajaAlreadyOpen(BigInteger cajaId) throws NotFoundException {
        Caja caja = cajaPort.findById(cajaId);
        if(caja == null){
            CajaIdNotFoundException errorNotFound = new CajaIdNotFoundException();
            errorNotFound.addParams(cajaId);
            throw errorNotFound;
        }
        AperturaCaja lastRecord = aperturaCajaPort.getLastRecord(caja.getId());
        return lastRecord!=null && lastRecord.getEstado();
    }
}
