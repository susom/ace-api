package com.podalv.search.server.api.responses;

public class ServerStatusResponse {

  private static String OK_RESPONSE = "OK";
  private String        status;
  private boolean       workshop;

  public static ServerStatusResponse createOkResponse(final boolean workshop) {
    return new ServerStatusResponse(OK_RESPONSE, workshop);
  }

  public ServerStatusResponse(final String status, final boolean workshop) {
    this.status = status;
    this.workshop = workshop;
  }

  public String getStatus() {
    return status;
  }

  public boolean getWorkshop() {
    return workshop;
  }

  public void setWorkshop(final boolean workshop) {
    this.workshop = workshop;
  }

  public void setStatus(final String status) {
    this.status = status;
  }

  public boolean isOk() {
    return status.equals(OK_RESPONSE);
  }
}
