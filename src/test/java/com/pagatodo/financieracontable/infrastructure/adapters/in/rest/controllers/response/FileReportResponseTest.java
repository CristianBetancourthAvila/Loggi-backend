package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class FileReportResponseTest {

    @Test
    @DisplayName("Test for FileReportResponse model")
    void testModelFileReportResponse() {
        FileReportResponse fileReportResponse = new FileReportResponse();
        fileReportResponse.setFile("FileContent");
        fileReportResponse.setFileName("ReportFile.pdf");
        fileReportResponse.setContentType("application/pdf");

        assertDoesNotThrow(() -> PropertyTester.test(fileReportResponse));
    }
}
