package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.TrasladoEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.time.LocalDate;

public interface TrasladoRepository extends JpaRepository<TrasladoEntity, Integer> {

    @Query("SELECT T FROM TrasladoEntity T WHERE (:date IS NULL OR T.fechaCreacion = :date)")
    Page<TrasladoEntity> findTrasladosByDate(@Param("date") LocalDate date, Pageable pageable);

    @Query("SELECT T FROM TrasladoEntity T WHERE " +
            "(:send = true AND T.cajaOrigen.id = :cajaId) OR " +
            "(:send = false AND T.cajaDestino.id = :cajaId)")
    Page<TrasladoEntity> findSendReceiveTrasladosByCaja(@Param("cajaId") BigInteger cajaId, @Param("send") Boolean send, Pageable pageable);
}
