package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.traslado;

import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.TrasladoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.traslado.TrasladoMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.TrasladoRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.traslado.TrasladoPort;
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
public class TrasladoAdapter implements TrasladoPort {

    private final TrasladoMapper trasladoMapper;

    private final TrasladoRepository trasladoRepository;

    @Override
    public Traslado create(Traslado traslado) {
        TrasladoEntity trasladoEntity = trasladoMapper.domainToEntity(traslado);
        TrasladoEntity trasladoSaved = trasladoRepository.save(trasladoEntity);
        return trasladoMapper.entityToDomain(trasladoSaved);
    }

    @Override
    public PageModel<List<Traslado>> findTrasladosByDate(LocalDate date, Integer rowsPerPage, Integer skip) {
        int pageNumber = (int) Math.ceil((double)skip/rowsPerPage);
        Pageable pageable = PageRequest.of(pageNumber, rowsPerPage);

        Page<TrasladoEntity> page = trasladoRepository.findTrasladosByDate(date, pageable);
        return new PageModel<>(trasladoMapper.entitiesToDomains(page.getContent()), BigInteger.valueOf(page.getTotalElements()));
    }

    @Override
    public PageModel<List<Traslado>> findSendReceiveTrasladosByCaja(BigInteger cajaId, Boolean send, Integer rowsPerPage, Integer skip) {
        int pageNumber = (int) Math.ceil((double)skip/rowsPerPage);
        Pageable pageable = PageRequest.of(pageNumber, rowsPerPage);

        Page<TrasladoEntity> page = trasladoRepository.findSendReceiveTrasladosByCaja(cajaId,send,pageable);
        return new PageModel<>(trasladoMapper.entitiesToDomains(page.getContent()), BigInteger.valueOf(page.getTotalElements()));
    }
}
