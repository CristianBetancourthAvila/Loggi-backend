package com.pagatodo.financieracontable.infrastructure.ports.out.ingreso;

import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface IngresoPort {

    Ingreso save(Ingreso ingreso);

    void updateCancellationReason(Integer id, String cancellationReason);

    Ingreso findById(Integer id);

    List<Ingreso> findIngresosByDateAndUsuarioAndCaja(LocalDate date, BigInteger usuarioId, BigInteger cajaId);

    List<Ingreso> findAllByDateBetweenAndLibroDiarioFilter(LibroDiarioFilter libroDiarioFilter);
}
