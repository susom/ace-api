package com.podalv.search.server.api.timeintervals;

import com.podalv.search.server.api.datastructures.additionaldata.AdditionalData;

/** Contains a start and an end (double) of day offsets from patient's date of birth and additional data for the event
 *
 * @author podalv
 *
 */
public class TimeIntervalWithData<S extends AdditionalData> extends TimeInterval {

  private final S additionalData;

  public TimeIntervalWithData(final double start, final double end, final S additionalData) {
    super(start, end);
    this.additionalData = additionalData;
  }

  public S getAdditionalData() {
    return additionalData;
  }

}
