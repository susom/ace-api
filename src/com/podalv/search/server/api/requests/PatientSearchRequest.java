package com.podalv.search.server.api.requests;

import java.util.Arrays;

import com.google.gson.Gson;

public class PatientSearchRequest {

  String          query;
  private boolean returnPids          = true;
  private boolean returnTimeIntervals = false;
  private boolean returnSurvivalData  = false;
  private int     pidCntLimit         = Integer.MAX_VALUE;
  private int     statisticsLimit     = Integer.MAX_VALUE;
  private int[]   encounterBuckets;
  private int[]   durationBuckets;
  private boolean binary              = false;
  private int[]   searchablePids;

  public static PatientSearchRequest create(final String query) {
    final PatientSearchRequest result = new PatientSearchRequest();
    result.setQuery(query);
    return result;
  }

  public void setEncounterBuckets(final int[] encounterBuckets) {
    this.encounterBuckets = encounterBuckets;
  }

  public void setDurationBuckets(final int[] durationBuckets) {
    this.durationBuckets = durationBuckets;
  }

  public int[] getDurationBuckets() {
    return durationBuckets;
  }

  public int[] getEncounterBuckets() {
    return encounterBuckets;
  }

  public void setBinary(final boolean binary) {
    this.binary = binary;
  }

  public boolean isBinarySearchRequest() {
    return binary;
  }

  public void setStatisticsLimit(final int statisticsLimit) {
    this.statisticsLimit = statisticsLimit;
  }

  public boolean isReturnSurvivalData() {
    return this.returnSurvivalData;
  }

  public int getStatisticsLimit() {
    return statisticsLimit;
  }

  public void setReturnSurvivalData(final boolean returnSurvivalData) {
    this.returnSurvivalData = returnSurvivalData;
  }

  public void setPidCntLimit(final int pidCntLimit) {
    this.pidCntLimit = pidCntLimit;
  }

  public int getPidCntLimit() {
    return pidCntLimit;
  }

  public void setSearchablePids(final int[] searchablePids) {
    this.searchablePids = searchablePids;
  }

  public int[] getSearchablePids() {
    return searchablePids;
  }

  public String getQuery() {
    return query.trim();
  }

  public void setReturnTimeIntervals(final boolean returnTimeIntervals) {
    this.returnTimeIntervals = returnTimeIntervals;
  }

  public void setReturnPids(final boolean returnPids) {
    this.returnPids = returnPids;
  }

  public boolean isReturnPids() {
    return this.returnPids;
  }

  public boolean isReturnTimeIntervals() {
    return this.returnTimeIntervals;
  }

  public boolean isPidRequest() {
    return false;
  }

  public boolean isBinaryPidRequest() {
    return binary;
  }

  public void setQuery(final String query) {
    this.query = query;
  }

  public static PatientSearchRequest copy(final PatientSearchRequest originalRequest, final String newQueryText) {
    final PatientSearchRequest response = new PatientSearchRequest();
    response.query = newQueryText;
    response.returnPids = originalRequest.returnPids;
    response.returnTimeIntervals = originalRequest.returnTimeIntervals;
    response.returnSurvivalData = originalRequest.returnSurvivalData;
    response.statisticsLimit = originalRequest.statisticsLimit;
    response.pidCntLimit = originalRequest.pidCntLimit;
    response.binary = originalRequest.binary;
    if (originalRequest.durationBuckets != null) {
      response.durationBuckets = Arrays.copyOf(originalRequest.durationBuckets, originalRequest.durationBuckets.length);
    }
    if (originalRequest.encounterBuckets != null) {
      response.encounterBuckets = Arrays.copyOf(originalRequest.encounterBuckets, originalRequest.encounterBuckets.length);
    }
    if (originalRequest.searchablePids != null) {
      response.searchablePids = Arrays.copyOf(originalRequest.searchablePids, originalRequest.searchablePids.length);
    }
    return response;
  }

  @Override
  public int hashCode() {
    return query.hashCode();
  }

  private boolean compareStrings(final String str1, final String str2) {
    return (str1 == null && str2 == null) || (str1 != null && str2 != null && str1.equals(str2));
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj != null && obj instanceof PatientSearchRequest) {
      final PatientSearchRequest req = (PatientSearchRequest) obj;
      return compareStrings(query, req.query) && req.returnPids == returnPids && req.returnTimeIntervals == returnTimeIntervals;
    }
    return false;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

}