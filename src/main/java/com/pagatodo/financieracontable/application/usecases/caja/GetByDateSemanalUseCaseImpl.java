package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetByDateSemanalUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GetByDateSemanalUseCaseImpl implements GetByDateSemanalUseCase {

    private final CajaPort cajaPort;

    private final GetByDateMensualUseCaseImpl getByDateMensualUseCase;

    @Transactional(readOnly = true)
    @Override
    public FileReport findByDateSemanal(LocalDate from, LocalDate to) throws CajaNotFoundException, JRException, FileNotFoundException {
        return getByDateMensualUseCase.generateReport(cajaPort.findAllByDateBetween(from, to));
    }
}
