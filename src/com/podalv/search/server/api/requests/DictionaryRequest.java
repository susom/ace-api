package com.podalv.search.server.api.requests;

public class DictionaryRequest {

  private String[] cpt;
  private String[] icd9;
  private String[] atc;
  private String[] labs;
  private String[] rxNorm;

  public String[] getAtc() {
    return atc;
  }

  public String[] getCpt() {
    return cpt;
  }

  public String[] getIcd9() {
    return icd9;
  }

  public String[] getLabs() {
    return labs;
  }

  public String[] getRxNorm() {
    return rxNorm;
  }

  public void setAtc(final String[] atc) {
    this.atc = atc;
  }

  public void setCpt(final String[] cpt) {
    this.cpt = cpt;
  }

  public void setIcd9(final String[] icd9) {
    this.icd9 = icd9;
  }

  public void setLabs(final String[] labs) {
    this.labs = labs;
  }

  public void setRxNorm(final String[] rxNorm) {
    this.rxNorm = rxNorm;
  }

}
