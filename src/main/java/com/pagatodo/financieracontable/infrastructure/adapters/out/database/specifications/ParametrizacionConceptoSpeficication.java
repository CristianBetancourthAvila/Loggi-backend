package com.pagatodo.financieracontable.infrastructure.adapters.out.database.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ParametrizacionConceptoEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ParametrizacionConceptoSpeficication implements Specification<ParametrizacionConceptoEntity> {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoNombreConcepto;
	private String tipoConcepto;

    public ParametrizacionConceptoSpeficication(String codigoNombreConcepto, String tipo) {
    	this.codigoNombreConcepto = codigoNombreConcepto;
    	this.tipoConcepto = tipo;
    }
    
	@Override
	public Predicate toPredicate(Root<ParametrizacionConceptoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		        
        Predicate predicate = criteriaBuilder.conjunction();

        if (tipoConcepto != null && !tipoConcepto.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("tipo")), "%" + tipoConcepto.toLowerCase() + "%"));
        }
        
        if (codigoNombreConcepto != null && !codigoNombreConcepto.isEmpty()) {
        	
            Predicate codigoNombrePredicate = criteriaBuilder.like(
                    criteriaBuilder.function(
                        "CONCAT",
                        String.class,
                        root.get("codigoConcepto"),
                        criteriaBuilder.literal("-"),
                        criteriaBuilder.lower(root.get("nombreConcepto"))
                    ),
                    "%" + codigoNombreConcepto.toLowerCase() + "%"
                );
            predicate = criteriaBuilder.and(predicate,codigoNombrePredicate);
        }

        
        Path<String> orderById = root.get("id");
        query.orderBy(criteriaBuilder.asc(orderById));

        return predicate;
	}
	
    public static Specification<ParametrizacionConceptoEntity> applyFilters(String codigoNombreConcepto, String tipo) {
        return new ParametrizacionConceptoSpeficication(codigoNombreConcepto,tipo);
    }


}
