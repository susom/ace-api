package com.podalv.search.server.api.requests;

import com.google.gson.Gson;

/** Request to dump selected patients
 *
 * @author podalv
 *
 */
public class DumpRequest {

  private final int patientId;
  private boolean   icd9;
  private boolean   cpt;
  private boolean   rx;
  private boolean   snomed;
  private boolean   notes;
  private boolean   visitTypes;
  private boolean   noteTypes;
  private boolean   encounterDays;
  private boolean   ageRanges;
  private boolean   labs;
  private boolean   vitals;
  private boolean   atc;
  private int[]     yearRanges;
  private String    selectionQuery;
  private boolean   containsStart;
  private boolean   containsEnd;

  public static DumpRequest createFull(final int patientId) {
    final DumpRequest req = new DumpRequest(patientId);
    req.setAgeRanges(true);
    req.setAtc(true);
    req.setCpt(true);
    req.setEncounterDays(true);
    req.setIcd9(true);
    req.setLabs(true);
    req.setNotes(true);
    req.setRx(true);
    req.setSnomed(true);
    req.setVisitTypes(true);
    req.setVitals(true);
    req.setNoteTypes(true);
    req.setYearRanges(new int[] {0, 2100});
    return req;
  }

  public void setContainsEnd(final boolean containsEnd) {
    this.containsEnd = containsEnd;
  }

  public void setContainsStart(final boolean containsStart) {
    this.containsStart = containsStart;
  }

  public void setSelectionQuery(final String selectionQuery) {
    this.selectionQuery = selectionQuery;
  }

  public void setYearRanges(final int[] yearRanges) {
    this.yearRanges = yearRanges;
  }

  public DumpRequest(final int patientId) {
    this.patientId = patientId;
  }

  public void setAtc(final boolean atc) {
    this.atc = atc;
  }

  public void setVitals(final boolean vitals) {
    this.vitals = vitals;
  }

  public void setLabs(final boolean labs) {
    this.labs = labs;
  }

  public void setNoteTypes(final boolean noteTypes) {
    this.noteTypes = noteTypes;
  }

  public void setAgeRanges(final boolean ageRanges) {
    this.ageRanges = ageRanges;
  }

  public void setSnomed(final boolean snomed) {
    this.snomed = snomed;
  }

  public void setEncounterDays(final boolean encounterDays) {
    this.encounterDays = encounterDays;
  }

  public void setVisitTypes(final boolean visitTypes) {
    this.visitTypes = visitTypes;
  }

  public void setNotes(final boolean notes) {
    this.notes = notes;
  }

  public void setRx(final boolean rx) {
    this.rx = rx;
  }

  public void setCpt(final boolean cpt) {
    this.cpt = cpt;
  }

  public void setIcd9(final boolean icd9) {
    this.icd9 = icd9;
  }

  public int getPatientId() {
    return patientId;
  }

  public boolean isIcd9() {
    return icd9;
  }

  public boolean isRx() {
    return rx;
  }

  public boolean isNotes() {
    return notes;
  }

  public boolean isSnomed() {
    return snomed;
  }

  public boolean isCpt() {
    return cpt;
  }

  public boolean isVisitTypes() {
    return visitTypes;
  }

  public boolean isEncounterDays() {
    return encounterDays;
  }

  public boolean isAgeRanges() {
    return ageRanges;
  }

  public boolean isLabs() {
    return labs;
  }

  public boolean isContainsEnd() {
    return containsEnd;
  }

  public boolean isContainsStart() {
    return containsStart;
  }

  public String getSelectionQuery() {
    return selectionQuery;
  }

  public boolean isVitals() {
    return vitals;
  }

  public boolean isNoteTypes() {
    return noteTypes;
  }

  public int[] getYearRanges() {
    return yearRanges;
  }

  public boolean isAtc() {
    return atc;
  }

  public static void main(final String[] args) {
    final DumpRequest req = DumpRequest.createFull(1);
    req.setAgeRanges(true);
    req.setAtc(true);
    req.setIcd9(true);
    req.setRx(true);
    req.setCpt(true);
    req.setEncounterDays(true);
    req.setLabs(true);
    req.setNotes(true);
    req.setNoteTypes(true);
    req.setSnomed(true);
    req.setVisitTypes(true);
    req.setVitals(true);
    System.out.println(new Gson().toJson(req));
  }

}