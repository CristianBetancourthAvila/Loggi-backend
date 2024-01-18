package com.pagatodo.financieracontable.application.usecases.caja;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.FileReport;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetByDateMensualUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetByDateMensualUseCaseImpl implements GetByDateMensualUseCase {

    private final CajaPort cajaPort;
    private static final String REPORT_TEMPLATE_PATH = "/templates/caja_report_excel.jrxml";

    @Transactional(readOnly = true)
    @Override
    public FileReport findByDateMensual(LocalDate date) throws CajaNotFoundException, FileNotFoundException, JRException {
        List<Caja> cajas = cajaPort.findAllByDateYearAndMonth(date.getYear(), date.getMonthValue());
        return generateReport(cajas);
    }

    //TODO: corregir en el reporte los valores quemados
    public FileReport generateReport(List<Caja> listaCajas) throws JRException {
        InputStream inputStream = getClass().getResourceAsStream(REPORT_TEMPLATE_PATH);
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaCajas);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        byte[] excelBytes = outputStream.toByteArray();
        String base64String = Base64.getEncoder().encodeToString(excelBytes);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        var dateTimeNow = LocalDateTime.now().format(formatter);
        FileReport fileReport = new FileReport();
        fileReport.setFile(base64String);
        fileReport.setContentType("application/octet-stream");
        fileReport.setFileName("Reporte_Cajas_"+dateTimeNow+".xlsx");
        return fileReport;
    }

}
