package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.CajaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface CajaRepository extends JpaRepository<CajaEntity, BigInteger>{

    @Transactional
    @Modifying
    @Query("UPDATE CajaEntity CE SET CE.estado = :estado WHERE CE.id = :id")
    void updateStatus(@Param("id") BigInteger id, @Param("estado") Estado estado);

    @Query("SELECT CE FROM CajaEntity CE WHERE CE.puntoVentaTerminalId = :pdvTerminalId")
    CajaEntity getCajaByPdvTerminalId(@Param("pdvTerminalId") BigInteger pdvTerminalId);

    @Query("SELECT CE FROM CajaEntity CE WHERE (:codigoCaja IS NULL OR CE.codigoCaja = :codigoCaja)" +
            "AND (:puntoVentaTerminalId IS NULL OR CE.puntoVentaTerminalId = :puntoVentaTerminalId)")
    List<CajaEntity> findAllByFilter(@Param("codigoCaja") String codigoCaja, @Param("puntoVentaTerminalId") Long puntoVentaTerminalId);

    List<CajaEntity> findByFechaCreacionBetween(LocalDate from, LocalDate to);

    @Query("SELECT CE FROM CajaEntity CE " +
            "WHERE YEAR(CE.fechaCreacion) = :year " +
            "AND MONTH(CE.fechaCreacion) = :month")
    List<CajaEntity> findByMonthAndYear(@Param("year") int year, @Param("month") int month);

    @Query("SELECT c FROM CajaEntity c " +
            "JOIN ProgramarPagoCajaEntity p ON c.id = p.caja.id " +
            "WHERE p.programarPago.id = :programarPagoId")
    List<CajaEntity> findCajasByProgramarPagoId(@Param("programarPagoId") Integer programarPagoId);

    @Query("SELECT CE FROM CajaEntity CE WHERE CE.puntoVentaTerminalId IN :pdvTerminalIds ORDER BY CE.puntoVentaTerminalId ASC")
    List<CajaEntity> findAllCajasByPdvTerminalIds(@Param("pdvTerminalIds") List<Long> pdvTerminalIds);

    @Query("SELECT new com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.CajaProjection(CE.id, CE.codigoCaja, CE.nombreCaja) FROM CajaEntity CE WHERE " +
            "(:filterText IS NULL OR UPPER(CE.codigoCaja) LIKE %:filterText% OR UPPER(CE.nombreCaja) LIKE %:filterText%) " +
            "AND (:status IS NULL OR CE.estado = :status)"+
            "ORDER BY CE.codigoCaja ASC")
    List<CajaProjection> findAllByMatchesAndStatus(@Param("filterText") String filterText, @Param("status") Estado status);

    @Query("SELECT new com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.CajaProjection(CE.id, CE.codigoCaja, CE.nombreCaja) FROM CajaEntity CE WHERE " +
            "(:filterText IS NULL OR UPPER(CE.codigoCaja) LIKE %:filterText% OR UPPER(CE.nombreCaja) LIKE %:filterText%) " +
            "AND (:status IS NULL OR CE.estado = :status)" +
            "ORDER BY CE.codigoCaja ASC " +
            "LIMIT 10")
    List<CajaProjection> findByTop10AndMatchesAndStatus(@Param("filterText") String filterText, @Param("status") Estado status);
}
