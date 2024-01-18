package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.anulacion;

import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AnulacionEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.anulacion.AnulacionMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.AnulacionRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.anulacion.AnulacionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AnulacionAdapter implements AnulacionPort {

    private final AnulacionMapper anulacionMapper;

    private final AnulacionRepository anulacionRepository;

    @Override
    public Anulacion create(Anulacion anulacion) {

        AnulacionEntity anulacionEntity = anulacionMapper.domainToEntity(anulacion);
        AnulacionEntity anulacionSaved = anulacionRepository.save(anulacionEntity);

        return anulacionMapper.entityToDomain(anulacionSaved);
    }

    @Override
    public PageModel<List<Anulacion>> findAnulacionesByCriteria(TipoMovimiento movementType, LocalDate date, EstadoAnulacion status,Integer rowsPerPage, Integer skip) {
        Integer pageNumber = (int) Math.ceil((double)skip/rowsPerPage);
        Pageable pageable = PageRequest.of(pageNumber, rowsPerPage);

        Page<AnulacionEntity> page = anulacionRepository.findAnulacionesByCriteria(movementType,date,status, pageable);
        return new PageModel<>(anulacionMapper.entitiesToDomains(page.getContent()), BigInteger.valueOf(page.getTotalElements()));
    }

    @Override
    public void updateAuthorizerUser(Integer id, Integer userId, EstadoAnulacion status) {
         anulacionRepository.updateAuthorizerUser(id, userId, status);
    }


    @Override
    public Anulacion findById(Integer id) {
        return anulacionMapper.entityToDomain(anulacionRepository.findById(id).orElse(null));
    }
}
