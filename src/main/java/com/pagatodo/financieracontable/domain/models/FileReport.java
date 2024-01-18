package com.pagatodo.financieracontable.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileReport {
    private String file;
    private String fileName;
    private String contentType;
}
