package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto.helpers.ParametrizacionConceptoTable;
import com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto.helpers.ParametrizacionTableMappers;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.ExportType;
import com.pagatodo.financieracontable.infrastructure.ports.in.parametrizacionconcepto.GenerateReportUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
@RequiredArgsConstructor
public class GenerateReportUseCaseImpl implements GenerateReportUseCase {

	private final ParametrizacionConceptoPort parametrizacionConceptoPort;

	private final ParametrizacionTableMappers mapper;

	@Override
	public FileReport execute(ExportType type, String codigoNombreConcepto, String tipoConcepto) throws JRException {

		List<ParametrizacionConcepto> result = parametrizacionConceptoPort.findAllByCodigoConceptoAndTipo(codigoNombreConcepto, tipoConcepto);

		List<ParametrizacionConceptoTable> table = result.stream().map(mapper::buildParametrizacionTable).toList();

		FileReport report = new FileReport();

		if (!table.isEmpty()) {
			report = exportReport(table, type);
		}

		return report;
	}

	private FileReport exportReport(Collection<?> beanCollection, ExportType exportType) throws JRException {

		FileReport resultReport = new FileReport();

		InputStream transactionReportStream = getClass().getResourceAsStream(
				"/templates/parametrizacion_report_" + exportType.toString().toLowerCase() + ".jrxml");

		String titleTransactionBy = "Reporte Parametrizacion Concepto";

		JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(beanCollection);

		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("title", titleTransactionBy);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		var dateTimeNow = LocalDateTime.now().format(formatter);
		var fileName = titleTransactionBy.replace(" ", "") + dateTimeNow;

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		if (exportType == ExportType.PDF) {

			JRPdfExporter exporter = new JRPdfExporter();

			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
			exporter.exportReport();

			resultReport.setContentType("application/pdf");
			resultReport.setFileName(fileName + ".pdf");

		} else if (exportType == ExportType.EXCEL) {

			JRXlsxExporter exporter = new JRXlsxExporter();

			SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
			reportConfigXLS.setSheetNames(new String[] { titleTransactionBy });
			reportConfigXLS.setDetectCellType(true);
			reportConfigXLS.setCollapseRowSpan(false);
			exporter.setConfiguration(reportConfigXLS);
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
			exporter.exportReport();

			resultReport.setContentType("application/octet-stream");
			resultReport.setFileName(fileName + ".xlsx");

		} else {
			
			JRCsvExporter exporter = new JRCsvExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput((new SimpleWriterExporterOutput(byteArrayOutputStream)));
			exporter.exportReport();

			resultReport.setContentType("text/csv");
			resultReport.setFileName(fileName + ".csv");

		}

		byte[] pdfByteArray = byteArrayOutputStream.toByteArray();

		resultReport.setFile(Base64.getEncoder().encodeToString(pdfByteArray));

		return resultReport;
	}

}
