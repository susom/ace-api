package com.podalv.search.server.api.datastructures.additionaldata;


/** Primary or not primary diagnosis code for Icd9 codes in visits
 *
 * @author podalv
 *
 */
public class Icd9AdditionalData implements AdditionalData {

  private final boolean primary;

  public static String  ICD9_PRIMARY_STRING = "PRIMARY";
  public static String  ICD9_OHER_STRING    = "OTHER";

  public Icd9AdditionalData(final String data) {
    primary = (data != null && data.equals(ICD9_PRIMARY_STRING)) ? true : false;
  }

  /** Returns an indicator whether the icd9 code was flagged as primary during the visit
   *
   * @return true is the code was flagged as primary in the visit
   */
  public boolean isPrimary() {
    return primary;
  }
}
