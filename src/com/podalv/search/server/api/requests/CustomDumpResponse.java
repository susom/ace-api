package com.podalv.search.server.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomDumpResponse {

  @JsonProperty("error") private String  error;
  @JsonProperty("result") private String result;

  public void setError(final String error) {
    this.error = error;
  }

  public void setResult(final String result) {
    this.result = result;
  }

  public String getError() {
    return error;
  }

  public String getResult() {
    return result;
  }

  public static CustomDumpResponse createError(final String error) {
    final CustomDumpResponse result = new CustomDumpResponse();
    result.setError(error);
    return result;
  }
}
