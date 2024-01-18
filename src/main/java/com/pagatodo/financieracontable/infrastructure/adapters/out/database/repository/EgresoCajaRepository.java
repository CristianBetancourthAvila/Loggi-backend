package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.EgresoCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface EgresoCajaRepository extends JpaRepository<EgresoCajaEntity, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE EgresoCajaEntity EC SET EC.motivoAnulacion = :motivoAnulacion WHERE EC.id = :id")
    void updateStatus(@Param("id") Integer id, @Param("motivoAnulacion") String motivoAnulacion);

    @Query("SELECT E FROM EgresoCajaEntity E WHERE E.fechaCreacion = :fechaCreacion " +
            "AND (:usuarioId IS NULL OR E.aperturaCaja.usuarioId = :usuarioId) " +
            "AND (:cajaId IS NULL OR E.aperturaCaja.caja.id = :cajaId)")
    List<EgresoCajaEntity> findByFechaCreacionAndUserIdAndCajaId(
            @Param("fechaCreacion") LocalDate fechaCreacion,
            @Param("usuarioId") BigInteger usuarioId,
            @Param("cajaId") BigInteger cajaId);

    @Query("SELECT E FROM EgresoCajaEntity E WHERE E.fechaCreacion BETWEEN :from AND :to " +
            "AND (:usuarioId IS NULL OR E.aperturaCaja.usuarioId = :usuarioId) " +
            "AND (:cajaId IS NULL OR E.aperturaCaja.caja.id = :cajaId)")
    List<EgresoCajaEntity> findByFechaCreacionBetweenAndUserIdAndCajaId(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            @Param("usuarioId") BigInteger usuarioId,
            @Param("cajaId") BigInteger cajaId);
}
