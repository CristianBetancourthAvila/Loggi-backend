package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class FileReportTest {

    @Test
    @DisplayName("Test for FileReport model")
    void testModelFileReport() {
        FileReport fileReport = new FileReport();
        fileReport.setFile("FileContent");
        fileReport.setFileName("ReportFile.pdf");
        fileReport.setContentType("application/pdf");

        assertDoesNotThrow(() -> PropertyTester.test(fileReport));
    }
}
