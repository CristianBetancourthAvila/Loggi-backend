package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.filter.CajaFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetCajaByCriteriaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCajaByCriteriaUseCaseImpl implements GetCajaByCriteriaUseCase {

    private final CajaPort cajaPort;

    @Transactional(readOnly = true)
    @Override
    public PageModel<List<Caja>> findWithFiler(CajaFilter cajaFilter, Integer rowsPerPage, Integer skip) throws CajaNotFoundException {
        List<Caja> cajaList = cajaPort.findAllByCodeAndPVTId(cajaFilter.getCodigoCaja(), cajaFilter.getPuntoVentaId());
        if (cajaList.isEmpty()){
            throw new CajaNotFoundException();
        }
        //TODO: falta implementar un cliente feing para poder filtrar stp,zona
        List<Caja> paginatedResult = cajaList.subList(skip, Math.min(skip + rowsPerPage, cajaList.size()));
        return new PageModel<>(paginatedResult, BigInteger.valueOf(cajaList.size()));
    }
}
