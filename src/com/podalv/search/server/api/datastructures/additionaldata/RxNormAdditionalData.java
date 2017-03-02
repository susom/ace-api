package com.podalv.search.server.api.datastructures.additionaldata;

/** Prscriptions can contain drug status (continued, discontinued, etc.) and the drug route (intravenous, oral, etc.)
 *
 * @author podalv
 *
 */
public class RxNormAdditionalData implements AdditionalData {

  private final String drugStatus;
  private final String drugRoute;

  public RxNormAdditionalData(final String drugRoute, final String drugStatus) {
    this.drugRoute = drugRoute;
    this.drugStatus = drugStatus;
  }

  public String getDrugRoute() {
    return drugRoute;
  }

  public String getDrugStatus() {
    return drugStatus;
  }

}
