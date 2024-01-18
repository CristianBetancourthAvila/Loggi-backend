package com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja;

import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface EgresoCajaPort {
    EgresoCaja save(EgresoCaja egresoCaja);
    void updateMotivo(Integer id, String motivoAnulacion) throws EgresoCajaNotFoundException;
    EgresoCaja findById(Integer id) throws EgresoCajaNotFoundException;
    List<EgresoCaja> findEgresosByDateAndUsuarioAndCaja(LocalDate date, BigInteger usuarioId, BigInteger cajaId);
    List<EgresoCaja> findAllByDateBetweenAndLibroDiarioFilter(LibroDiarioFilter libroDiarioFilter);
}
