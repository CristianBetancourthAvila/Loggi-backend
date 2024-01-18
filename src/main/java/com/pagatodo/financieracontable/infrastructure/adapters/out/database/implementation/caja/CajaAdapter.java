package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CajaEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.caja.CajaMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.CajaRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CajaAdapter implements CajaPort {

    private final CajaRepository cajaRepository;
    private final CajaMapper cajaMapper;

    @Override
    public List<Caja> saveAll(List<Caja> cajaList) {
        List<CajaEntity> list = cajaMapper.domainsToEntities(cajaList);
        List<CajaEntity> listSaved = cajaRepository.saveAll(list);
        return cajaMapper.entitiesToDomains(listSaved);
    }

    @Override
    public Caja findById(BigInteger id) {
        CajaEntity caja = cajaRepository.findById(id).orElse(null);
        return cajaMapper.entityToDomain(caja);
    }

    @Override
    @Transactional
    public Caja save(Caja caja) {
        CajaEntity cajaEntity =  cajaMapper.domainToEntity(caja);
        return cajaMapper.entityToDomain(cajaRepository.save(cajaEntity));
    }

    @Override
    public void updateStatus(BigInteger id, Estado estado) {
        cajaRepository.updateStatus(id,estado);
    }

    @Override
    public Caja getCajaByPdvTerminalId(BigInteger pdvTerminalId) {
        CajaEntity caja = cajaRepository.getCajaByPdvTerminalId(pdvTerminalId);
        return cajaMapper.entityToDomain(caja);
    }

    @Override
    public List<Caja> findAllByCodeAndPVTId(String code, Long puntoVentaTerminalId) {
        return cajaMapper.entitiesToDomains(cajaRepository.findAllByFilter(code, puntoVentaTerminalId));
    }

    @Override
    public List<Caja> findAllByDateBetween(LocalDate from, LocalDate to) throws CajaNotFoundException {
        List<CajaEntity> cajaList = cajaRepository.findByFechaCreacionBetween(from, to);
        return Optional.of(cajaList)
                .filter(list -> !list.isEmpty())
                .map(cajaMapper::entitiesToDomains)
                .orElseThrow(CajaNotFoundException::new);
    }

    @Override
    public List<Caja> findAllByDateYearAndMonth(int year, int month) throws CajaNotFoundException {
        List<CajaEntity> cajaList = cajaRepository.findByMonthAndYear(year, month);
        return Optional.of(cajaList)
                .filter(list -> !list.isEmpty())
                .map(cajaMapper::entitiesToDomains)
                .orElseThrow(CajaNotFoundException::new);
    }

    @Override
    public List<Caja> findCajasByProgramarPagoid(Integer programarPagoId) throws CajaNotFoundException {
        List<CajaEntity> cajas = cajaRepository.findCajasByProgramarPagoId(programarPagoId);
        if (cajas.isEmpty()) {
            throw new CajaNotFoundException();
        }
        return cajaMapper.entitiesToDomains(cajas);
    }

    @Override
    public List<Caja> findAllByPVTerminalId(List<Long> pdvTerminalIds) {
        return cajaMapper.entitiesToDomains(cajaRepository.findAllCajasByPdvTerminalIds(pdvTerminalIds));
    }

    @Override
    public List<Caja> findAllByMatchesAndStatus(String filterText, Estado status) {
        return cajaMapper.cajaProjectionsToDomains(cajaRepository.findAllByMatchesAndStatus(filterText, status));
    }

    @Override
    public List<Caja> findByTop10AndMatchesAndStatus(String filterText, Estado status) {
        return cajaMapper.cajaProjectionsToDomains(cajaRepository.findByTop10AndMatchesAndStatus(filterText, status));
    }

    @Override
    public Caja update(Caja caja) throws CajaIdNotFoundException {
        CajaIdNotFoundException cajaIdNotFoundException =  new CajaIdNotFoundException();
        cajaIdNotFoundException.addParams(caja.getId());
        CajaEntity target = cajaRepository.findById(caja.getId()).orElseThrow(() -> cajaIdNotFoundException);
        cajaMapper.mergeToEntity(target, caja);
        return cajaMapper.entityToDomain(cajaRepository.save(target));
    }
}