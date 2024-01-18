package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ProgramarPagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface ProgramarPagoRepository extends JpaRepository<ProgramarPagoEntity, Integer> {

	@Query("SELECT pp FROM ProgramarPagoEntity pp WHERE pp.usuarioTerceroId = :terceroId AND pp.estado = 'ACTIVO' AND pp.condicion = 'NO_PAGADO'")
    List<ProgramarPagoEntity> findAllByCriteria(@Param("terceroId") BigInteger terceroId);

    @Query("""
			select pp from ProgramarPagoEntity pp 
			WHERE (:status is NULL OR pp.estado = :status) 
			AND (:fechaAplicacion is NULL OR pp.fechaAplicacion = :fechaAplicacion)
			AND (:condicion is NULL OR pp.condicion = :condicion)
			AND (:codigoConcepto is NULL OR pp.parametrizacionConcepto.codigoConcepto = :codigoConcepto)
			""")
    List<ProgramarPagoEntity> findWithFilter(@Param("status") Estado status, @Param("fechaAplicacion") LocalDate fechaAplicacion, @Param("condicion") Condicion condicion, @Param("codigoConcepto") Long codigoConcepto);

	@Transactional
	@Modifying
	@Query("UPDATE ProgramarPagoEntity pp SET pp.estado = :estado WHERE pp.id = :id")
	void updateStatus(@Param("id") Integer id, @Param("estado") Estado estado);
}
