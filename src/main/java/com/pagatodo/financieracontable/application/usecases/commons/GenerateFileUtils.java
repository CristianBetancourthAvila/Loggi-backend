package com.pagatodo.financieracontable.application.usecases.commons;

import com.pagatodo.financieracontable.domain.models.FileReport;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

public class GenerateFileUtils {

    private GenerateFileUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static FileReport exportFileAsPDF(String templateName, Map<String, Object> parameters, String title, Collection<?> beanCollection) throws JRException {

        String fullRoute = "/templates/"+templateName;

        InputStream transactionReportStream = GenerateFileUtils.class.getResourceAsStream(fullRoute);

        JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(beanCollection);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollection!=null ? beanColDataSource : new JREmptyDataSource());

        var fileName = title.replace(" ", "");

        return exportJasperFileAsPdf(jasperPrint, fileName);
    }


    private static FileReport exportJasperFileAsPdf(JasperPrint jasperPrint, String fileName) throws JRException {
        FileReport resultReport = new FileReport();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.exportReport();

        resultReport.setContentType("application/pdf");
        resultReport.setFileName(fileName + ".pdf");

        byte[] pdfByteArray = byteArrayOutputStream.toByteArray();

        resultReport.setFile(Base64.getEncoder().encodeToString(pdfByteArray));

        return resultReport;
    }
}
