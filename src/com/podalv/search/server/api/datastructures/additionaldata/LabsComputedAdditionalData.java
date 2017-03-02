package com.podalv.search.server.api.datastructures.additionaldata;

/** Computed values for labs (such as "HIGH", "LOW", etc.)
 *
 * @author podalv
 *
 */
public class LabsComputedAdditionalData implements AdditionalData {

  private final String computedValue;

  public LabsComputedAdditionalData(final String computedValue) {
    this.computedValue = computedValue;
  }

  public String getComputedValue() {
    return computedValue;
  }
}
