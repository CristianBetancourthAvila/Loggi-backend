package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AnulacionEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface AnulacionRepository extends JpaRepository<AnulacionEntity, Integer> {

    @Query("SELECT A FROM AnulacionEntity A WHERE (:movementType IS NULL OR A.tipoMovimiento = :movementType)" +
            "AND (:date IS NULL OR A.fechaCreacion = :date)" +
            "AND (:status IS NULL OR A.estado = :status)")
    Page<AnulacionEntity> findAnulacionesByCriteria(
            @Param("movementType") TipoMovimiento movementType, @Param("date") LocalDate date,
            @Param("status")EstadoAnulacion status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE AnulacionEntity A SET A.autorizadorId = :userId, A.estado = :status WHERE A.id = :id")
    void updateAuthorizerUser(@Param("id") Integer id, @Param("userId") Integer userId, @Param("status") EstadoAnulacion status);
}
