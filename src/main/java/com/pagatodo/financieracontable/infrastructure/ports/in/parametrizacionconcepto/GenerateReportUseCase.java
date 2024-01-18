package com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto;

import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.enums.ExportType;

import net.sf.jasperreports.engine.JRException;

public interface GenerateReportUseCase {
	
	FileReport execute(ExportType type, String codigoNombreConcepto, String tipoConcepto) throws JRException;

}
