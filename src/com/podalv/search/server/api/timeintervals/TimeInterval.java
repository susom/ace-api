package com.podalv.search.server.api.timeintervals;

/** Time interval for an event in days from the date of birth.
 *  Time points will have the same start and end
 *
 * @author podalv
 *
 */
public class TimeInterval {

  private final double start;
  private final double end;

  public TimeInterval(final double start, final double end) {
    this.start = start;
    this.end = end;
  }

  public double getStart() {
    return start;
  }

  public double getEnd() {
    return end;
  }

}
