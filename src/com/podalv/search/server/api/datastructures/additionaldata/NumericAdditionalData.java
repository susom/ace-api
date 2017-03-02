package com.podalv.search.server.api.datastructures.additionaldata;

/** Any numeric value (interger or double) assigned to either a lab or a vitals reading
 *
 * @author podalv
 *
 */
public class NumericAdditionalData implements AdditionalData {

  private final double value;

  public NumericAdditionalData(final double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}
