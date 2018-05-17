package com.podalv.search.server.api.responses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Complete patient's record
 *
 * @author podalv
 *
 */
public class DumpResponse extends SerializableResponse {

  @JsonProperty("patientId") private final long                             patientId;
  @JsonProperty("recordStart") private int                                  recordStart;
  @JsonProperty("recordEnd") private int                                    recordEnd;
  @JsonProperty("death") private int                                        death;
  @JsonProperty("gender") private String                                    gender;
  @JsonProperty("race") private String                                      race;
  @JsonProperty("ethnicity") private String                                 ethnicity;
  @JsonProperty("icd9") private HashMap<String, ArrayList<String>>          icd9           = null;
  @JsonProperty("icd10") private HashMap<String, ArrayList<String>>         icd10          = null;
  @JsonProperty("cpt") private HashMap<String, ArrayList<Integer>>          cpt            = null;
  @JsonProperty("rx") private HashMap<String, ArrayList<String>>            rx             = null;
  @JsonProperty("snomed") private HashMap<String, ArrayList<Integer>>       snomed         = null;
  @JsonProperty("negatedTerms") private HashMap<String, ArrayList<String>>  negatedTerms   = null;
  @JsonProperty("fhTerms") private HashMap<String, ArrayList<String>>       fhTerms        = null;
  @JsonProperty("positiveTerms") private HashMap<String, ArrayList<String>> positiveTerms  = null;
  @JsonProperty("visitTypes") private HashMap<String, ArrayList<Integer>>   visitTypes     = null;
  @JsonProperty("departments") private HashMap<String, ArrayList<Integer>>  departments    = null;
  @JsonProperty("noteTypes") private HashMap<String, ArrayList<Integer>>    noteTypes      = null;
  @JsonProperty("atc") private HashMap<String, ArrayList<Integer>>          atc            = null;
  @JsonProperty("labs") private HashMap<String, ArrayList<String>>          labs           = null;
  @JsonProperty("labsRaw") private HashMap<String, ArrayList<String>>       labsRaw        = null;
  @JsonProperty("vitals") private HashMap<String, ArrayList<String>>        vitals         = null;
  @JsonProperty("encounterDays") private ArrayList<Integer>                 encounterDays  = null;
  @JsonProperty("ageRanges") private List<Integer>                          ageRanges      = null;
  @JsonProperty("yearRanges") private HashMap<Integer, ArrayList<Integer>>  yearRanges     = null;
  @JsonProperty("error") private String                                     error          = null;
  @JsonProperty("selectionQuery") private String                            selectionQuery = null;
  @JsonProperty("containsStart") private boolean                            containsStart  = false;
  @JsonProperty("containsEnd") private boolean                              containsEnd    = false;

  public static DumpResponse createError(final String error) {
    final DumpResponse result = new DumpResponse(-1);
    result.setError(error);
    return result;
  }

  public DumpResponse(final long patientId) {
    this.patientId = patientId;
  }

  public void setLabsRaw(final HashMap<String, ArrayList<String>> labs) {
    labsRaw = labs;
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

  public void setSelectionQuery(final String selectionQuery) {
    this.selectionQuery = selectionQuery;
  }

  public void setContainsEnd(final boolean containsEnd) {
    this.containsEnd = containsEnd;
  }

  public void setContainsStart(final boolean containsStart) {
    this.containsStart = containsStart;
  }

  public HashMap<String, ArrayList<String>> getLabsRaw() {
    return labsRaw;
  }

  public void setDepartments(final HashMap<String, ArrayList<Integer>> departments) {
    this.departments = departments;
  }

  public void setIcd10(final HashMap<String, ArrayList<String>> icd10) {
    this.icd10 = icd10;
  }

  public void setYearRanges(final HashMap<Integer, ArrayList<Integer>> yearRanges) {
    this.yearRanges = yearRanges;
  }

  public HashMap<Integer, ArrayList<Integer>> getYearRanges() {
    return yearRanges;
  }

  public List<Integer> getAgeRanges() {
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

  public void setAgeRanges(final List<Integer> ageRanges) {
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

  public HashMap<String, ArrayList<Integer>> getDepartments() {
    return departments;
  }

  public HashMap<String, ArrayList<String>> getIcd10() {
    return icd10;
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
  public void close() throws IOException {
    if (binaryResponse) {
      throw new UnsupportedOperationException();
    }
  }

}
