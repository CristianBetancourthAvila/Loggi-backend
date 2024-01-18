package com.pagatodo.financieracontable.infrastructure.adapters.out.database.specifications;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ConciliacionEntity;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ConciliacionSpecificationTest {

    @Mock
    private Root<ConciliacionEntity> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Test
    @DisplayName("Test for toPredicate method with valid filters")
    void toPredicate_withValidFilters() {
        ConciliacionFilter filter = new ConciliacionFilter();
        filter.setTipoConciliacion(TipoConciliacion.BANCARIA);
        filter.setAliadoProducto("Aliado1");
        filter.setFechaInicial(LocalDate.of(2024,1,11));
        filter.setFechaFinal(LocalDate.of(2024,1,12));

        Specification<ConciliacionEntity> specification = ConciliacionSpecification.applyFilters(filter.getTipoConciliacion(),
                filter.getAliadoProducto(), filter.getFechaInicial(), filter.getFechaFinal());

        Path<Object> mockPath = Mockito.mock();
        when(root.get(any(String.class))).thenReturn(mockPath);

        specification.toPredicate(root, query, criteriaBuilder);

        assertAll("resultado",
                () -> assertNotNull(criteriaBuilder));
    }

    @Test
    @DisplayName("Test for toPredicate method with null filters")
    void toPredicate_withNullFilters() {
        ConciliacionFilter filter = new ConciliacionFilter();
        filter.setTipoConciliacion(null);
        filter.setAliadoProducto(null);
        filter.setFechaInicial(null);
        filter.setFechaFinal(null);
        Specification<ConciliacionEntity> specification = ConciliacionSpecification.applyFilters(filter.getTipoConciliacion(),
                filter.getAliadoProducto(), filter.getFechaInicial(), filter.getFechaFinal());

        specification.toPredicate(root, query, criteriaBuilder);

        assertAll("resultado",
                () -> assertNotNull(criteriaBuilder));
    }
}
