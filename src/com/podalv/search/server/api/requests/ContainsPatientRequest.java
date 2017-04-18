package com.podalv.search.server.api.requests;

public class ContainsPatientRequest {

  private int patientId;

  public ContainsPatientRequest(final int pid) {
    this.patientId = pid;
  }

  public int getPatientId() {
    return patientId;
  }

  public void setPatientId(final int patientId) {
    this.patientId = patientId;
  }

}
