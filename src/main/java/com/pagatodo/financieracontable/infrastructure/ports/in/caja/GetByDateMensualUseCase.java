package com.pagatodo.financieracontable.infrastructure.ports.in.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.FileReport;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.time.LocalDate;

public interface GetByDateMensualUseCase {
    FileReport findByDateMensual(LocalDate date) throws CajaNotFoundException, IOException, JRException;
}
