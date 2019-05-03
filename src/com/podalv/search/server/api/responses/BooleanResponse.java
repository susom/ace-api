package com.podalv.search.server.api.responses;

public class BooleanResponse {

  private Boolean response;
  private String  error;

  public BooleanResponse(final String error) {
    this.error = error;
    response = null;
  }

  public BooleanResponse(final boolean response) {
    this.response = response;
  }

  public void setResponse(final boolean response) {
    this.response = response;
  }

  public boolean getResponse() {
    return response;
  }

  public String getError() {
    return error;
  }

  public void setError(final String error) {
    this.error = error;
  }
}
