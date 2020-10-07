package com.podalv.search.server.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class CustomDumpRequest {

  public static String                            DEFAULT_COLUMN_SEPARATOR = "\t";
  public static String                            DEFAULT_ROW_SEPARATOR    = "\n";
  public static String                            DEFAULT_QUOTE            = "";
  @JsonProperty("patientIds") private long[]      patientIds;
  @JsonProperty("header") private String[]        header;
  @JsonProperty("columns") private String[]       columns;
  @JsonProperty("columnSeparator") private String columnSeparator;
  @JsonProperty("rowSeparator") private String    rowSeparator;
  @JsonProperty("quote") private String           quote;
  @JsonProperty("compressFile") private boolean   compressFile             = false;

  public String[] getColumns() {
    return columns;
  }

  public long[] getPatientIds() {
    return patientIds;
  }

  public CustomDumpRequest setCompressFile(final boolean compress) {
    compressFile = compress;
    return this;
  }

  public String getColumnSeparator() {
    return columnSeparator == null ? DEFAULT_COLUMN_SEPARATOR : columnSeparator;
  }

  public String getRowSeparator() {
    return rowSeparator == null ? DEFAULT_ROW_SEPARATOR : rowSeparator;
  }

  public String getQuote() {
    return quote == null ? DEFAULT_QUOTE : quote;
  }

  public String[] getHeader() {
    return header;
  }

  public void setColumns(final String[] columns) {
    this.columns = columns;
  }

  public void setPatientIds(final long[] patientIds) {
    this.patientIds = patientIds;
  }

  public void setColumnSeparator(final String columnSeparator) {
    this.columnSeparator = columnSeparator;
  }

  public void setRowSeparator(final String rowSeparator) {
    this.rowSeparator = rowSeparator;
  }

  public void setHeader(final String[] header) {
    this.header = header;
  }

  public void setQuote(final String quote) {
    this.quote = quote;
  }

  public static void main(final String[] args) {
    final CustomDumpRequest request = new CustomDumpRequest();
    request.columns = new String[] {"PID", "ICD9", "CPT"};
    request.header = new String[] {"Patient Id", "ICD9 code", "CPT code"};
    request.patientIds = new long[] {111};
    System.out.println(new Gson().toJson(request));
  }

  public boolean isCompressFile() {
    return compressFile;
  }
}
