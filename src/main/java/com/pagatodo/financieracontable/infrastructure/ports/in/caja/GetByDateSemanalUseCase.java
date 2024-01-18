package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.FileReport;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public interface GetByDateSemanalUseCase {
    FileReport findByDateSemanal(LocalDate from, LocalDate to) throws CajaNotFoundException, JRException, FileNotFoundException;
}
