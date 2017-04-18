package com.podalv.search.server.api.responses;

import java.util.HashMap;

public class DictionaryResponse {

  private HashMap<String, String> cpt;
  private HashMap<String, String> icd9;
  private HashMap<String, String> atc;
  private HashMap<String, String> labs;
  private HashMap<String, String> vitals;
  private HashMap<String, String> rxNorm;

  public void setAtc(final HashMap<String, String> atc) {
    this.atc = atc;
  }

  public void setCpt(final HashMap<String, String> cpt) {
    this.cpt = cpt;
  }

  public void setIcd9(final HashMap<String, String> icd9) {
    this.icd9 = icd9;
  }

  public void setLabs(final HashMap<String, String> labs) {
    this.labs = labs;
  }

  public void setRxNorm(final HashMap<String, String> rxNorm) {
    this.rxNorm = rxNorm;
  }

  public void setVitals(final HashMap<String, String> vitals) {
    this.vitals = vitals;
  }

  public HashMap<String, String> getAtc() {
    return atc;
  }

  public HashMap<String, String> getCpt() {
    return cpt;
  }

  public HashMap<String, String> getIcd9() {
    return icd9;
  }

  public HashMap<String, String> getLabs() {
    return labs;
  }

  public HashMap<String, String> getRxNorm() {
    return rxNorm;
  }

  public HashMap<String, String> getVitals() {
    return vitals;
  }
}
