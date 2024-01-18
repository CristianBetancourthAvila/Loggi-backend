package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProgramarPagoCajaRepository extends JpaRepository<ProgramarPagoCajaEntity, Integer> {

    ProgramarPagoCajaEntity findByProgramarPagoIdAndCajaId(Integer programarPagoId, BigInteger cajaId);
}
