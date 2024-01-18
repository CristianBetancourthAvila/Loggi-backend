package com.pagatodo.financieracontable.infrastructure.ports.out.caja;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface CajaPort {
    List<Caja> saveAll(List<Caja> list);
    Caja findById(BigInteger id);
    Caja save(Caja caja);
    void updateStatus(BigInteger id, Estado estado);
    Caja getCajaByPdvTerminalId(BigInteger pdvTerminalId);
    List<Caja> findAllByCodeAndPVTId(String code, Long puntoVentaTerminalId);
    List<Caja> findAllByDateBetween(LocalDate from, LocalDate to) throws CajaNotFoundException;
    List<Caja> findAllByDateYearAndMonth(int year, int month) throws CajaNotFoundException;
    List<Caja> findCajasByProgramarPagoid(Integer programarPagoId) throws CajaNotFoundException;
    List<Caja> findAllByPVTerminalId(List<Long> pdvTerminalIds);
    List<Caja> findAllByMatchesAndStatus(String filterText, Estado status);
    List<Caja> findByTop10AndMatchesAndStatus(String filterText, Estado status);
    Caja update(Caja caja) throws CajaIdNotFoundException;
}
