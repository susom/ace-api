package com.podalv.search.server.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomDumpRequest {

  @JsonProperty("patientIds") private long[]      patientIds;
  @JsonProperty("header") private String[]        header;
  @JsonProperty("columns") private String[]       columns;
  @JsonProperty("columnSeparator") private String columnSeparator;
  @JsonProperty("rowSeparator") private String    rowSeparator;
  @JsonProperty("quote") private String           quote;

  public String[] getColumns() {
    return columns;
  }

  public long[] getPatientIds() {
    return patientIds;
  }

  public String getColumnSeparator() {
    return columnSeparator;
  }

  public String getRowSeparator() {
    return rowSeparator;
  }

  public String getQuote() {
    return quote;
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
}
