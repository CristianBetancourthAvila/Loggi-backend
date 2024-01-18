package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ParametrizacionConceptoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.ConceptoProjection;

public interface ParametrizacionConceptoRepository extends JpaRepository<ParametrizacionConceptoEntity, Long>,JpaSpecificationExecutor<ParametrizacionConceptoEntity>{
	

	@Query("""
			SELECT new com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.ConceptoProjection(pc.codigoConcepto, pc.nombreConcepto,pc.id)
			from ParametrizacionConceptoEntity pc 
			WHERE (:codigoConcepto is NULL OR CONCAT(pc.codigoConcepto,'') LIKE %:codigoConcepto%) 
			AND (:nombreConcepto is NULL OR pc.nombreConcepto LIKE %:nombreConcepto%)
			ORDER BY pc.codigoConcepto ASC
			""")
	List<ConceptoProjection> findByCodigoConceptoAndNombreConcepto(@Param("codigoConcepto") Long codigoConcepto, @Param("nombreConcepto") String nombreConcepto);
	
	@Query("""
			SELECT new com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.ConceptoProjection(pc.codigoConcepto, pc.nombreConcepto,pc.id)
			from ParametrizacionConceptoEntity pc
			ORDER BY pc.codigoConcepto ASC
			LIMIT 10
			""")
	List<ConceptoProjection> findByTop10OrderByCodigoConcepto();
}
