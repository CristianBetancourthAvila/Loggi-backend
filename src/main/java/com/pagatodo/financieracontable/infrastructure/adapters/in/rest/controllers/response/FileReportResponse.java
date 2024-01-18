package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileReportResponse {
    private String file;
    private String fileName;
    private String contentType;
}
