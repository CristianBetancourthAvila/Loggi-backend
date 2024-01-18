package com.pagatodo.financieracontable.application.usecases.programarpago;

import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.filter.ProgramarPagoFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.in.programarpago.GetPPByCriteriaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPPByCriteriaUseCaseImpl implements GetPPByCriteriaUseCase {

    private final ProgramarPagoPort programarPagoPort;
    @Transactional(readOnly = true)
    @Override
    public PageModel<List<ProgramarPago>> findWithFiler(ProgramarPagoFilter programarPagoFilter, Integer rowsPerPage, Integer skip) {
        List<ProgramarPago> programarPagosList = programarPagoPort.findWithFilter(programarPagoFilter);
        //TODO: Falta filtrar por concepto egreso con un servicio
        List<ProgramarPago> paginatedResult = programarPagosList.subList(skip, Math.min(skip + rowsPerPage, programarPagosList.size()));
        return new PageModel<>(paginatedResult, BigInteger.valueOf(programarPagosList.size()));
    }
}
