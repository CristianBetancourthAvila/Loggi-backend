package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.IngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface IngresoRepository extends JpaRepository<IngresoEntity, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE IngresoEntity I SET I.motivoAnulacion = :cancellationReason WHERE I.id = :id")
    void updateCancellationReason(@Param("id") Integer id, @Param("cancellationReason") String cancellationReason);

    @Query("SELECT I FROM IngresoEntity I WHERE I.fechaCreacion = :fechaCreacion " +
            "AND (:usuarioId IS NULL OR I.aperturaCaja.usuarioId = :usuarioId) " +
            "AND (:cajaId IS NULL OR I.aperturaCaja.caja.id = :cajaId)")
    List<IngresoEntity> findByFechaCreacionAndUserIdAndCajaId(
            @Param("fechaCreacion") LocalDate fechaCreacion,
            @Param("usuarioId") BigInteger usuarioId,
            @Param("cajaId") BigInteger cajaId);

    @Query("SELECT I FROM IngresoEntity I WHERE I.fechaCreacion BETWEEN :from AND :to " +
            "AND (:usuarioId IS NULL OR I.aperturaCaja.usuarioId = :usuarioId) " +
            "AND (:cajaId IS NULL OR I.aperturaCaja.caja.id = :cajaId)")
    List<IngresoEntity> findByFechaCreacionBetweenAndUserIdAndCajaId(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            @Param("usuarioId") BigInteger usuarioId,
            @Param("cajaId") BigInteger cajaId);
}
