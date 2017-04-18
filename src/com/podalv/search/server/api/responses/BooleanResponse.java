package com.podalv.search.server.api.responses;

public class BooleanResponse {

  private boolean response;

  public BooleanResponse(final boolean response) {
    this.response = response;
  }

  public void setResponse(final boolean response) {
    this.response = response;
  }

  public boolean getResponse() {
    return this.response;
  }

}
