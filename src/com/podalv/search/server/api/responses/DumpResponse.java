package com.podalv.search.server.api.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/** Complete patient's record
 *
 * @author podalv
 *
 */
public class DumpResponse {

  private final long                           patientId;
  private int                                  recordStart;
  private int                                  recordEnd;
  private int                                  death;
  private String                               gender;
  private String                               race;
  private String                               ethnicity;
  private HashMap<String, ArrayList<String>>   icd9           = null;
  private HashMap<String, ArrayList<Integer>>  cpt            = null;
  private HashMap<String, ArrayList<String>>   rx             = null;
  private HashMap<String, ArrayList<Integer>>  snomed         = null;
  private HashMap<String, ArrayList<String>>   negatedTerms   = null;
  private HashMap<String, ArrayList<String>>   fhTerms        = null;
  private HashMap<String, ArrayList<String>>   positiveTerms  = null;
  private HashMap<String, ArrayList<Integer>>  visitTypes     = null;
  private HashMap<String, ArrayList<Integer>>  noteTypes      = null;
  private HashMap<String, ArrayList<Integer>>  atc            = null;
  private HashMap<String, ArrayList<String>>   labs           = null;
  private HashMap<String, ArrayList<String>>   labsRaw        = null;
  private HashMap<String, ArrayList<String>>   vitals         = null;
  private ArrayList<Integer>                   encounterDays  = null;
  private ArrayList<Integer>                   ageRanges      = null;
  private HashMap<Integer, ArrayList<Integer>> yearRanges     = null;
  private String                               error          = null;
  private String                               selectionQuery = null;
  private boolean                              containsStart;
  private boolean                              containsEnd;

  public static DumpResponse createError(final String error) {
    final DumpResponse result = new DumpResponse(-1);
    result.setError(error);
    return result;
  }

  public DumpResponse(final long patientId) {
    this.patientId = patientId;
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

  public String getSelectionQuery() {
    return selectionQuery;
  }

  public boolean isContainsEnd() {
    return containsEnd;
  }

  public boolean isContainsStart() {
    return containsStart;
  }

  public void setLabsRaw(final HashMap<String, ArrayList<String>> labs) {
    labsRaw = labs;
  }

  public HashMap<String, ArrayList<String>> getLabsRaw() {
    return labsRaw;
  }

  public void setYearRanges(final HashMap<Integer, ArrayList<Integer>> yearRanges) {
    this.yearRanges = yearRanges;
  }

  public HashMap<Integer, ArrayList<Integer>> getYearRanges() {
    return yearRanges;
  }

  public ArrayList<Integer> getAgeRanges() {
    return ageRanges;
  }

  public void setDeath(final int death) {
    this.death = death;
  }

  public int getDeath() {
    return death;
  }

  public void setEthnicity(final String ethnicity) {
    this.ethnicity = ethnicity;
  }

  public void setNoteTypes(final HashMap<String, ArrayList<Integer>> noteTypes) {
    this.noteTypes = noteTypes;
  }

  public HashMap<String, ArrayList<Integer>> getNoteTypes() {
    return noteTypes;
  }

  public void setGender(final String gender) {
    this.gender = gender;
  }

  public void setRace(final String race) {
    this.race = race;
  }

  public String getEthnicity() {
    return ethnicity;
  }

  public String getGender() {
    return gender;
  }

  public String getRace() {
    return race;
  }

  public void setLabs(final HashMap<String, ArrayList<String>> labs) {
    this.labs = labs;
  }

  public void setAtc(final HashMap<String, ArrayList<Integer>> atc) {
    this.atc = atc;
  }

  public HashMap<String, ArrayList<Integer>> getAtc() {
    return atc;
  }

  public HashMap<String, ArrayList<String>> getLabs() {
    return labs;
  }

  public void setAgeRanges(final ArrayList<Integer> ageRanges) {
    this.ageRanges = ageRanges;
  }

  public void setRecordEnd(final int recordEnd) {
    this.recordEnd = recordEnd;
  }

  public void setVitals(final HashMap<String, ArrayList<String>> vitals) {
    this.vitals = vitals;
  }

  public HashMap<String, ArrayList<String>> getVitals() {
    return vitals;
  }

  public void setEncounterDays(final ArrayList<Integer> encounterDays) {
    this.encounterDays = encounterDays;
  }

  public ArrayList<Integer> getEncounterDays() {
    return encounterDays;
  }

  public void setFhTerms(final HashMap<String, ArrayList<String>> fhTerms) {
    this.fhTerms = fhTerms;
  }

  public HashMap<String, ArrayList<Integer>> getVisitTypes() {
    return visitTypes;
  }

  public void setVisitTypes(final HashMap<String, ArrayList<Integer>> visitTypes) {
    this.visitTypes = visitTypes;
  }

  public void setNegatedTerms(final HashMap<String, ArrayList<String>> negatedTerms) {
    this.negatedTerms = negatedTerms;
  }

  public void setPositiveTerms(final HashMap<String, ArrayList<String>> positiveTerms) {
    this.positiveTerms = positiveTerms;
  }

  public HashMap<String, ArrayList<String>> getFhTerms() {
    return fhTerms;
  }

  public HashMap<String, ArrayList<String>> getNegatedTerms() {
    return negatedTerms;
  }

  public HashMap<String, ArrayList<String>> getPositiveTerms() {
    return positiveTerms;
  }

  public void setRecordStart(final int recordStart) {
    this.recordStart = recordStart;
  }

  public int getRecordEnd() {
    return recordEnd;
  }

  public int getRecordStart() {
    return recordStart;
  }

  public void setError(final String error) {
    this.error = error;
  }

  public void setIcd9(final HashMap<String, ArrayList<String>> icd9) {
    this.icd9 = icd9;
  }

  public void setSnomed(final HashMap<String, ArrayList<Integer>> snomed) {
    this.snomed = snomed;
  }

  public HashMap<String, ArrayList<Integer>> getSnomed() {
    return snomed;
  }

  public void setRx(final HashMap<String, ArrayList<String>> rx) {
    this.rx = rx;
  }

  public HashMap<String, ArrayList<String>> getRx() {
    return rx;
  }

  public void setCpt(final HashMap<String, ArrayList<Integer>> cpt) {
    this.cpt = cpt;
  }

  public HashMap<String, ArrayList<String>> getIcd9() {
    return icd9;
  }

  public HashMap<String, ArrayList<Integer>> getCpt() {
    return cpt;
  }

  public long getPatientId() {
    return patientId;
  }

  public String getError() {
    return error;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(patientId);
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof DumpResponse) {
      final DumpResponse resp = (DumpResponse) obj;
      return resp.patientId == patientId && //
          resp.recordStart == recordStart && //
          resp.containsEnd == containsEnd && //
          resp.death == death && //
          resp.containsEnd == containsStart && //
          resp.containsEnd == containsEnd && //
          Objects.equals(resp.gender, gender) && //
          Objects.equals(resp.race, race) && //
          Objects.equals(resp.ethnicity, ethnicity) && //
          Objects.equals(resp.selectionQuery, selectionQuery) && //
          Objects.equals(resp.error, error) && //
          Objects.equals(resp.icd9, icd9) && //
          Objects.equals(resp.cpt, cpt) && //
          Objects.equals(resp.rx, rx) && //
          Objects.equals(resp.snomed, snomed) && //
          Objects.equals(resp.negatedTerms, negatedTerms) && //
          Objects.equals(resp.fhTerms, fhTerms) && //
          Objects.equals(resp.positiveTerms, positiveTerms) && //
          Objects.equals(resp.visitTypes, visitTypes) && //      
          Objects.equals(resp.noteTypes, noteTypes) && //
          Objects.equals(resp.atc, atc) && //
          Objects.equals(resp.labs, labs) && //
          Objects.equals(resp.labsRaw, labsRaw) && //
          Objects.equals(resp.vitals, vitals) && //
          Objects.equals(resp.encounterDays, encounterDays) && //
          Objects.equals(resp.ageRanges, ageRanges) && //
          Objects.equals(resp.yearRanges, yearRanges);
    }
    return false;
  }

}
