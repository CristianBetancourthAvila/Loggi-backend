package com.pagatodo.financieracontable.application.usecases.commons;

import com.pagatodo.financieracontable.domain.models.FileReport;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GenerateFileUtilsTest {

    @Test
    @DisplayName("Test for exporting a file report with success")
    void exportFileAsPDF_success() throws JRException {
            String templateName = "recaudo_comprobante_pdf.jrxml";
            String title = "Test Title";
            Map<String, Object> parameters = new HashMap<>();

            List<Object> beanCollection = new ArrayList<>();
            FileReport result = GenerateFileUtils.exportFileAsPDF(templateName, parameters, title, beanCollection);

            Assertions.assertNotNull(result);
            Assertions.assertEquals("application/pdf", result.getContentType());
            Assertions.assertTrue(result.getFileName().startsWith(title.replace(" ", "")));
    }

    @Test
    @DisplayName("Test for exporting a file report with an error called jr exception")
    void exportFileAsPDF_error_jr_exception(){
        String templateName = "archivo.jrxml";
        String title = "Test Title";
        Map<String, Object> parameters = new HashMap<>();

        List<Object> beanCollection = new ArrayList<>();

        assertThrows(JRException.class, () -> GenerateFileUtils.exportFileAsPDF(templateName, parameters, title, beanCollection));
    }
}
