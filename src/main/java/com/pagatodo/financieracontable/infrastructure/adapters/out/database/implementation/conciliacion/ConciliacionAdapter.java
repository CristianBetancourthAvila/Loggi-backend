package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.conciliacion;

import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ConciliacionEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.conciliacion.ConciliacionMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.ConciliacionRepository;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.specifications.ConciliacionSpecification;
import com.pagatodo.financieracontable.infrastructure.ports.out.conciliacion.ConciliacionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConciliacionAdapter implements ConciliacionPort {

    private final ConciliacionRepository conciliacionRepository;
    private final ConciliacionMapper conciliacionMapper;

    @Override
    public Conciliacion save(Conciliacion conciliacion) {
        ConciliacionEntity concilaicionEntity =  conciliacionMapper.domainToEntity(conciliacion);
        return conciliacionMapper.entityToDomain(conciliacionRepository.save(concilaicionEntity));
    }

    @Override
    public PageModel<List<Conciliacion>> findWithFilter(ConciliacionFilter conciliacionFilter, Integer rowsPerPage, Integer skip) {
        int pageNumber = (int) Math.ceil((double)skip/rowsPerPage);
        Pageable pageable = PageRequest.of(pageNumber, rowsPerPage);

        Specification<ConciliacionEntity> specifications = ConciliacionSpecification.applyFilters(conciliacionFilter.getTipoConciliacion(),
                conciliacionFilter.getAliadoProducto(),conciliacionFilter.getFechaInicial(),conciliacionFilter.getFechaFinal());

        Page<ConciliacionEntity> page = conciliacionRepository.findAll(specifications,pageable);

        return new PageModel<>(conciliacionMapper.entitiesToDomains(page.getContent()), BigInteger.valueOf(page.getTotalElements()));
    }
}
