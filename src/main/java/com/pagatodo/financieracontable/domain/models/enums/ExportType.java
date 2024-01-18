package com.pagatodo.financieracontable.domain.models.enums;

public enum ExportType {
	
  PDF("pdf", "application/pdf"),
  EXCEL("xlsx", "application/octet-stream"),
  CSV("csv", "text/csv");

  private final String extension;
  private final String contentType;

  ExportType(String extension, String contentType) {
    this.extension = extension;
    this.contentType = contentType;
  }

  public String getExtension() {
    return "." + extension;
  }

  public String getContentType() {
    return contentType;
  }
}
