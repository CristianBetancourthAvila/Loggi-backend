package com.pagatodo.financieracontable.infrastructure.adapters.out.database.specifications;

import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import org.springframework.data.jpa.domain.Specification;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ConciliacionEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;

public class ConciliacionSpecification implements Specification<ConciliacionEntity> {

    private static final long serialVersionUID = 2L;

    private final TipoConciliacion tipoConciliacion;
    private final String aliadoProducto;
    private final LocalDate fechaInicial;
    private final LocalDate fechaFinal;

    public ConciliacionSpecification(TipoConciliacion tipoConciliacion, String aliadoProducto, LocalDate fechaInicial, LocalDate fechaFinal) {
        this.tipoConciliacion = tipoConciliacion;
        this.aliadoProducto = aliadoProducto;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    @Override
    public Predicate toPredicate(Root<ConciliacionEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (tipoConciliacion != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("tipoConciliacion"), tipoConciliacion));
        }

        if (aliadoProducto != null && !aliadoProducto.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("aliadoProducto")),
                    "%" + aliadoProducto.toLowerCase() + "%"));
        }

        if (fechaInicial != null && fechaFinal != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.between(
                            root.get("fechaInicial"),
                            criteriaBuilder.literal(fechaInicial),
                            criteriaBuilder.literal(fechaFinal)
                    )
            );
        }

        Path<String> orderByPath = root.get("id");
        query.orderBy(criteriaBuilder.asc(orderByPath));

        return predicate;
    }

    public static Specification<ConciliacionEntity> applyFilters(TipoConciliacion tipoConciliacion, String aliadoProducto, LocalDate fechaInicial, LocalDate fechaFinal) {
        return new ConciliacionSpecification(tipoConciliacion, aliadoProducto, fechaInicial, fechaFinal);
    }
}
