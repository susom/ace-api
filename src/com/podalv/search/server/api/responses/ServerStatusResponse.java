package com.podalv.search.server.api.responses;

public class ServerStatusResponse {

  private static String OK_RESPONSE = "OK";
  private String        status;
  private String        datasetVersion;
  private String        version;
  private boolean       workshop;

  public String getStatus() {
    return status;
  }

  public String getVersion() {
    return version;
  }

  public boolean getWorkshop() {
    return workshop;
  }

  public String getDatasetVersion() {
    return datasetVersion;
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
