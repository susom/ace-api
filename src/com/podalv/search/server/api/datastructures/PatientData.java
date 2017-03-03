package com.podalv.search.server.api.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import com.podalv.search.server.api.datastructures.additionaldata.Icd9AdditionalData;
import com.podalv.search.server.api.datastructures.additionaldata.LabsComputedAdditionalData;
import com.podalv.search.server.api.datastructures.additionaldata.NumericAdditionalData;
import com.podalv.search.server.api.datastructures.additionaldata.RxNormAdditionalData;
import com.podalv.search.server.api.datastructures.additionaldata.TermAdditionalData;
import com.podalv.search.server.api.exceptions.QueryException;
import com.podalv.search.server.api.iterators.ImmutableIterator;
import com.podalv.search.server.api.responses.DumpResponse;
import com.podalv.search.server.api.timeintervals.TimeInterval;
import com.podalv.search.server.api.timeintervals.TimeIntervalWithData;

/** Contains all the data about the patient in the database. Can be generated from a DumpResponse
 *
 * @author podalv
 *
 */
public class PatientData {

  private final DumpResponse data;

  public static final double minutesToDays(final int age) {
    return (age == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (age < 0) ? 0 : age / (double) (24 * 60);
  }

  /** From a dump response creates PatientData object
   *
   * @param response
   * @return
   * @throws QueryException if the patient did not exist or the response did not return any data
   */
  public static PatientData create(final DumpResponse response) throws QueryException {
    if (response == null || response.getError() != null) {
      throw new QueryException(response == null ? "Patient dump request did not return any data" : "Patient dump request returned error '" + response.getError() + "'");
    }
    return new PatientData(response);
  }

  /** Returns time intervals with additional data about primary icd9 codes for the specified icd9 code
   *
   * @param icd9
   * @return
   */
  public Iterator<TimeIntervalWithData<Icd9AdditionalData>> getIcd9TimeIntervals(final String icd9) {
    final LinkedList<TimeIntervalWithData<Icd9AdditionalData>> result = new LinkedList<TimeIntervalWithData<Icd9AdditionalData>>();
    final ArrayList<String> list = data.getIcd9().get(icd9);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 3) {
        result.add(new TimeIntervalWithData<Icd9AdditionalData>(minutesToDays(Integer.parseInt(list.get(x))), minutesToDays(Integer.parseInt(list.get(x + 1))),
            new Icd9AdditionalData(list.get(x + 2))));
      }
    }
    return new ImmutableIterator<TimeIntervalWithData<Icd9AdditionalData>>(result.iterator());
  }

  /** Returns time intervals for all labs that had a computed value ("HIGH", "NORMAL", etc.)
   *  If the lab did not have a computed value, but a numeric value, use the getLabsNumericTimeIntervals method
   *
   * @param lab
   * @return
   */
  public Iterator<TimeIntervalWithData<LabsComputedAdditionalData>> getLabsComputedTimeIntervals(final String lab) {
    final LinkedList<TimeIntervalWithData<LabsComputedAdditionalData>> result = new LinkedList<TimeIntervalWithData<LabsComputedAdditionalData>>();
    final ArrayList<String> list = data.getLabs().get(lab);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 2) {
        result.add(new TimeIntervalWithData<LabsComputedAdditionalData>(minutesToDays(Integer.parseInt(list.get(x))), minutesToDays(Integer.parseInt(list.get(x))),
            new LabsComputedAdditionalData(list.get(x + 1))));
      }
    }
    return new ImmutableIterator<TimeIntervalWithData<LabsComputedAdditionalData>>(result.iterator());
  }

  /** Returns time intervals for all labs that had a numeric value
   *  The same lab could have had also a computed value. Use the getLabsComputedTimeIntervals method in that case
   *
   * @param lab
   * @return
   */
  public Iterator<TimeIntervalWithData<NumericAdditionalData>> getLabsNumericTimeIntervals(final String lab) {
    final LinkedList<TimeIntervalWithData<NumericAdditionalData>> result = new LinkedList<TimeIntervalWithData<NumericAdditionalData>>();
    final ArrayList<String> list = data.getLabsRaw().get(lab);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 2) {
        result.add(new TimeIntervalWithData<NumericAdditionalData>(minutesToDays(Integer.parseInt(list.get(x))), minutesToDays(Integer.parseInt(list.get(x))),
            new NumericAdditionalData(Double.parseDouble(list.get(x + 1)))));
      }
    }
    return new ImmutableIterator<TimeIntervalWithData<NumericAdditionalData>>(result.iterator());
  }

  /** Returns all time intervals for which a year was indicated
   *  The time intervals are not contiguous. Returns all time intervals for which the year was known, but does not merge them into continuous intervals
   *
   * @param year
   * @return
   */
  public Iterator<TimeInterval> getYearTimeIntervals(final int year) {
    final LinkedList<TimeInterval> result = new LinkedList<TimeInterval>();
    final ArrayList<Integer> list = data.getYearRanges().get(year);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 2) {
        result.add(new TimeInterval(minutesToDays(list.get(x)), minutesToDays(list.get(x))));
      }
    }
    return new ImmutableIterator<TimeInterval>(result.iterator());
  }

  /** For vitals name returns all time intervals and the numeric value of the vitals reading
   *
   * @param vitalsName
   * @return
   */
  public Iterator<TimeIntervalWithData<NumericAdditionalData>> getVitalsTimeIntervals(final String vitalsName) {
    final LinkedList<TimeIntervalWithData<NumericAdditionalData>> result = new LinkedList<TimeIntervalWithData<NumericAdditionalData>>();
    final ArrayList<String> list = data.getVitals().get(vitalsName);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 2) {
        result.add(new TimeIntervalWithData<NumericAdditionalData>(minutesToDays(Integer.parseInt(list.get(x))), minutesToDays(Integer.parseInt(list.get(x))),
            new NumericAdditionalData(Double.parseDouble(list.get(x + 1)))));
      }
    }
    return new ImmutableIterator<TimeIntervalWithData<NumericAdditionalData>>(result.iterator());
  }

  /** For the specified term returns time point, note id and note type where there was a positive mention of the term
   *
   * @param term
   * @return
   */
  public Iterator<TimeIntervalWithData<TermAdditionalData>> getPositiveTermTimeIntervals(final String term) {
    final LinkedList<TimeIntervalWithData<TermAdditionalData>> result = new LinkedList<TimeIntervalWithData<TermAdditionalData>>();
    final ArrayList<String> list = data.getPositiveTerms().get(term);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 3) {
        result.add(new TimeIntervalWithData<TermAdditionalData>(minutesToDays(Integer.parseInt(list.get(x))), minutesToDays(Integer.parseInt(list.get(x))), new TermAdditionalData(
            Integer.parseInt(list.get(x + 1)), list.get(x + 2))));
      }
    }
    return new ImmutableIterator<TimeIntervalWithData<TermAdditionalData>>(result.iterator());
  }

  /** For the specified term returns time point, note id and note type where there was a negated mention of the term
  *
  * @param term
  * @return
  */
  public Iterator<TimeIntervalWithData<TermAdditionalData>> getNegatedTermTimeIntervals(final String term) {
    final LinkedList<TimeIntervalWithData<TermAdditionalData>> result = new LinkedList<TimeIntervalWithData<TermAdditionalData>>();
    final ArrayList<String> list = data.getNegatedTerms().get(term);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 3) {
        result.add(new TimeIntervalWithData<TermAdditionalData>(minutesToDays(Integer.parseInt(list.get(x))), minutesToDays(Integer.parseInt(list.get(x))), new TermAdditionalData(
            Integer.parseInt(list.get(x + 1)), list.get(x + 2))));
      }
    }
    return new ImmutableIterator<TimeIntervalWithData<TermAdditionalData>>(result.iterator());
  }

  /** For the specified term returns time point, note id and note type where there was a family history mention of the term
  *
  * @param term
  * @return
  */
  public Iterator<TimeIntervalWithData<TermAdditionalData>> getFamilyHistoryTermTimeIntervals(final String term) {
    final LinkedList<TimeIntervalWithData<TermAdditionalData>> result = new LinkedList<TimeIntervalWithData<TermAdditionalData>>();
    final ArrayList<String> list = data.getFhTerms().get(term);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 3) {
        result.add(new TimeIntervalWithData<TermAdditionalData>(minutesToDays(Integer.parseInt(list.get(x))), minutesToDays(Integer.parseInt(list.get(x))), new TermAdditionalData(
            Integer.parseInt(list.get(x + 1)), list.get(x + 2))));
      }
    }
    return new ImmutableIterator<TimeIntervalWithData<TermAdditionalData>>(result.iterator());
  }

  /** For the specified RxNorm returns time interval, drug status and drug route
   *
   * @param rx
   * @return
   */
  public Iterator<TimeIntervalWithData<RxNormAdditionalData>> getRxNormTimeIntervals(final String rx) {
    final LinkedList<TimeIntervalWithData<RxNormAdditionalData>> result = new LinkedList<TimeIntervalWithData<RxNormAdditionalData>>();
    final ArrayList<String> list = data.getRx().get(rx);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 4) {
        result.add(new TimeIntervalWithData<RxNormAdditionalData>(minutesToDays(Integer.parseInt(list.get(x))), minutesToDays(Integer.parseInt(list.get(x + 1))),
            new RxNormAdditionalData(list.get(x + 2), list.get(x + 3))));
      }
    }
    return new ImmutableIterator<TimeIntervalWithData<RxNormAdditionalData>>(result.iterator());
  }

  /** For CPT code returns all time intervals when the CPT was indicated
   *
   * @param cpt
   * @return
   */
  public Iterator<TimeInterval> getCptTimeIntervals(final String cpt) {
    final LinkedList<TimeInterval> result = new LinkedList<TimeInterval>();
    final ArrayList<Integer> list = data.getCpt().get(cpt);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 2) {
        result.add(new TimeInterval(minutesToDays(list.get(x)), minutesToDays(list.get(x + 1))));
      }
    }
    return new ImmutableIterator<TimeInterval>(result.iterator());
  }

  /** Returns a list of RxNorms assigned to the specified ATC code
   *
   * @param atc
   * @return
   */
  public Iterator<Integer> getAtcRxNorms(final String atc) {
    final LinkedList<Integer> result = new LinkedList<Integer>();
    final ArrayList<Integer> list = data.getAtc().get(atc);
    if (list != null) {
      for (int x = 0; x < list.size(); x++) {
        result.add(list.get(x));
      }
    }
    return result.iterator();
  }

  /** For the specified snomed code (replacement of ICD9/ICD10 code) returns all time intervals when it was indicated
   *  Snomed codes unlike ICD9 codes do not have PRIMARY diagnosis information (the snomed to icd9 mapping is N->N which makes primary diagnosis flag impossible to
   *  propagate)
   *
   * @param snomed
   * @return
   */
  public Iterator<TimeInterval> getSnomedTimeIntervals(final String snomed) {
    final LinkedList<TimeInterval> result = new LinkedList<TimeInterval>();
    final ArrayList<Integer> list = data.getSnomed().get(snomed);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 2) {
        result.add(new TimeInterval(minutesToDays(list.get(x)), minutesToDays(list.get(x + 1))));
      }
    }
    return new ImmutableIterator<TimeInterval>(result.iterator());
  }

  /** Returns time intervals when a visit with the specified type occurred
   *
   * @param visitType
   * @return
   */
  public Iterator<TimeInterval> getVisitTypeTimeIntervals(final String visitType) {
    final LinkedList<TimeInterval> result = new LinkedList<TimeInterval>();
    final ArrayList<Integer> list = data.getVisitTypes().get(visitType);
    if (list != null) {
      for (int x = 0; x < list.size(); x += 2) {
        result.add(new TimeInterval(minutesToDays(list.get(x)), minutesToDays(list.get(x + 1))));
      }
    }
    return new ImmutableIterator<TimeInterval>(result.iterator());
  }

  /** Returns time points when patient had notes with the specified type
   *
   * @param noteType
   * @return
   */
  public Iterator<TimeInterval> getNoteTypeTimeIntervals(final String noteType) {
    final LinkedList<TimeInterval> result = new LinkedList<TimeInterval>();
    final ArrayList<Integer> list = data.getNoteTypes().get(noteType);
    if (list != null) {
      for (int x = 0; x < list.size(); x++) {
        result.add(new TimeInterval(minutesToDays(list.get(x)), minutesToDays(list.get(x))));
      }
    }
    return new ImmutableIterator<TimeInterval>(result.iterator());
  }

  private PatientData(final DumpResponse response) {
    this.data = response;
  }

  private Iterator<String> getUniqueCodes(final HashMap<String, ?> map) {
    return map != null ? new ImmutableIterator<String>(map.keySet().iterator()) : new ImmutableIterator<String>(null);
  }

  public int getPatientId() {
    return data.getPatientId();
  }

  /** Returns offset of the first data point for the patient from the date of birth
   *
   * @return
   */
  public double getRecordStart() {
    return minutesToDays(data.getRecordStart());
  }

  /** Returns offset of the last data point for the patient from the date of birth
   *
   * @return
   */
  public double getRecordEnd() {
    return data.getRecordEnd();
  }

  /**
   *
   * @return true if there is death record for the patient
   */
  public boolean hasDied() {
    return data.getDeath() > 0;
  }

  /** Returns gender string
   *
   * @return
   */
  public String getGender() {
    return data.getGender();
  }

  /** Returns ethnicity string
   *
   * @return
   */
  public String getEthnicity() {
    return data.getEthnicity();
  }

  private Iterator<TimeInterval> generateTimeIntervals(final ArrayList<Integer> array) {
    final LinkedList<TimeInterval> result = new LinkedList<TimeInterval>();
    if (array != null) {
      for (int x = 0; x < array.size(); x += 2) {
        result.add(new TimeInterval(minutesToDays(array.get(x)), minutesToDays(array.get(x + 1))));
      }
    }
    return result.iterator();
  }

  /** Returns time intervals of 24 hour length during which there was at least one datapoint for the patient
   *
   * @return
   */
  public Iterator<TimeInterval> getEncounterDays() {
    return generateTimeIntervals(data.getEncounterDays());
  }

  /** Returns all available time points for which the patient had at least one event
   *
   * @return
   */
  public Iterator<TimeInterval> getAgeRanges() {
    return generateTimeIntervals(data.getAgeRanges());
  }

  /** Returns race string
   *
   * @return
   */
  public String getRace() {
    return data.getRace();
  }

  /** Returns date of death. If the death date is not available, returns -1
   *
   * @return
   */
  public int getDeath() {
    return hasDied() ? data.getDeath() : -1;
  }

  /** Returns a list of unique ATC codes for the patient
   *
   * @return
   */
  public Iterator<String> getUniqueAtcCodes() {
    return getUniqueCodes(data.getAtc());
  }

  /** Returns a list of years for which there is at least 1 data point
   *
   * @return
   */
  public Iterator<Integer> getYearsWithData() {
    return data.getYearRanges() != null ? new ImmutableIterator<Integer>(data.getYearRanges().keySet().iterator()) : new ImmutableIterator<Integer>(null);
  }

  /** Returns a list of unique ICD9 codes for the patient
   *
   * @return
   */
  public Iterator<String> getUniqueIcd9Codes() {
    return getUniqueCodes(data.getIcd9());
  }

  /** Returns a list of unique CPT codes for the patient
   *
   * @return
   */
  public Iterator<String> getUniqueCptCodes() {
    return getUniqueCodes(data.getCpt());
  }

  /** Returns a list of unique RxNorm codes for the patient
   *
   * @return
   */
  public Iterator<String> getUniqueRxNormCodes() {
    return getUniqueCodes(data.getRx());
  }

  /** Returns a list of unique SNOMED codes for the patient
   *
   * @return
   */
  public Iterator<String> getUniqueSnomedCodes() {
    return getUniqueCodes(data.getSnomed());
  }

  /** Returns a list of unique terms for which the patient had at least 1 positive mention
   *
   * @return
   */
  public Iterator<String> getUniquePositiveTerms() {
    return getUniqueCodes(data.getPositiveTerms());
  }

  /** Returns a list of unique terms for which the patient had at least 1 negated mention
   *
   * @return
   */
  public Iterator<String> getUniqueNegatedTerms() {
    return getUniqueCodes(data.getNegatedTerms());
  }

  /** Returns a list of unique terms for which the patient had at least 1 family history mention
   *
   * @return
   */
  public Iterator<String> getUniqueFamilyHistoryTerms() {
    return getUniqueCodes(data.getFhTerms());
  }

  /** Returns a list of unique note types for the patient
   *
   * @return
   */
  public Iterator<String> getUniqueNoteTypes() {
    return getUniqueCodes(data.getNoteTypes());
  }

  /** Returns a list of unique visit types for the patient
   *
   * @return
   */
  public Iterator<String> getUniqueVisitTypes() {
    return getUniqueCodes(data.getVisitTypes());
  }

  private Iterator<String> mergeLabs() {
    final HashSet<String> result = new HashSet<String>();
    if (data.getLabs() != null) {
      result.addAll(data.getLabs().keySet());
    }
    if (data.getLabsRaw() != null) {
      result.addAll(data.getLabsRaw().keySet());
    }
    return result.iterator();
  }

  /** Returns a list of unique lab codes for which the patient had either a computed value or a numeric value
   *
   * @return
   */
  public Iterator<String> getUniqueLabCodes() {
    return mergeLabs();
  }

  /** Returns a list of unique vitals codes for the patient
   *
   * @return
   */
  public Iterator<String> getUniqueVitalsCodes() {
    return getUniqueCodes(data.getVitals());
  }

}
