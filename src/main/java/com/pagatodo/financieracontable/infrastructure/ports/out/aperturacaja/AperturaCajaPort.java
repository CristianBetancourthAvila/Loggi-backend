package com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja;

import com.pagatodo.financieracontable.domain.models.AperturaCaja;

import java.math.BigInteger;

public interface AperturaCajaPort {

    AperturaCaja create(AperturaCaja aperturaCaja);

    AperturaCaja getLastRecord(BigInteger cajaId);

    AperturaCaja findById(Long id);

    void updateStatus(Long id, boolean status);
}
