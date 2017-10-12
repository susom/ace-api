package com.podalv.search.server.api.datastructures;

import java.util.LinkedList;

import com.podalv.search.server.api.timeintervals.TimeInterval;

/** Contains patient id and optionally a list of time intervals for which the query was true
 *
 * @author podalv
 *
 */
public class PatientId {

  private final int                      patientId;
  private final LinkedList<TimeInterval> startEndIntervals = new LinkedList<>();

  public PatientId(final int patientId) {
    this.patientId = patientId;
  }

  public void addStartEndInterval(final TimeInterval ti) {
    this.startEndIntervals.add(ti);
  }

  public int getPatientId() {
    return patientId;
  }

  public LinkedList<TimeInterval> getStartEndIntervals() {
    return startEndIntervals;
  }

  @Override
  public boolean equals(final Object arg0) {
    return (arg0 != null && arg0 instanceof PatientId && ((PatientId) arg0).patientId == patientId);
  }

  @Override
  public int hashCode() {
    return patientId;
  }

}
