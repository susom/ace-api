package com.podalv.search.server.api;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.podalv.search.server.api.datastructures.PatientData;
import com.podalv.search.server.api.datastructures.PatientId;
import com.podalv.search.server.api.exceptions.QueryException;
import com.podalv.search.server.api.requests.DumpRequest;
import com.podalv.search.server.api.requests.PatientSearchRequest;
import com.podalv.search.server.api.responses.DumpResponse;
import com.podalv.search.server.api.responses.PatientSearchResponse;
import com.podalv.search.server.api.responses.ServerStatusResponse;
import com.podalv.search.server.api.timeintervals.TimeInterval;

/** Stores connection details to ATLAS server
 *
 * @author podalv
 *
 */
public class AtlasConnection {

  public static final String STATUS_QUERY = "status";
  public static final String DUMP_QUERY   = "dump";
  public static final String SEARCH_QUERY = "query";
  private final String       url;

  public AtlasConnection(final String url) {
    this.url = url;
  }

  /** Tests the connection to the server
   *
   * @return true if connection can be established
   * @throws IOException if connection is not successful
   */
  public boolean test() throws IOException {
    final String result = QueryUtils.query(url + "/" + STATUS_QUERY, "", -1);
    final ServerStatusResponse response = new Gson().fromJson(result, ServerStatusResponse.class);
    return response.isOk();
  }

  /** Returns an iterator of patient ids from a query
   *
   * @param query
   * @return Iterator<PatientId>
   * @throws IOException
   * @throws JsonSyntaxException
   */
  public Iterator<PatientId> getPatientIds(final String query) throws JsonSyntaxException, IOException, QueryException {
    final PatientSearchRequest request = new PatientSearchRequest();
    request.setQuery(query);
    request.setBinary(false);
    request.setPidCntLimit(Integer.MAX_VALUE);
    request.setReturnPids(true);
    request.setReturnSurvivalData(false);
    request.setReturnTimeIntervals(false);
    request.setStatisticsLimit(0);
    final PatientSearchResponse response = new Gson().fromJson(QueryUtils.query(url + "/" + SEARCH_QUERY, new Gson().toJson(request), -1), PatientSearchResponse.class);
    if (response.containsErrors()) {
      throw new QueryException(response.getErrorMessage());
    }
    final Iterator<double[]> iterator = response.getPatientIds().iterator();
    final HashMap<Integer, PatientId> map = new HashMap<Integer, PatientId>();
    while (iterator.hasNext()) {
      final double[] value = iterator.next();
      if (value.length != 1 && value.length != 3) {
        throw new QueryException("Malformed query response. PatientId response has " + value.length + " columns. Only either 1 or 3 are allowed");
      }
      final int id = (int) value[0];
      PatientId patientId = map.get(id);
      if (patientId == null) {
        patientId = new PatientId(id);
        map.put(id, patientId);
      }
      if (value.length == 3) {
        patientId.addStartEndInterval(new TimeInterval(value[1], value[2]));
      }
    }

    return map.values().iterator();
  }

  /** Downloads a file that is part of the search response for queries CSV and EVENTFLOW
  *
  * @param query
  * @param outputFile
  * @throws IOException
  * @throws JsonSyntaxException
  * @throws QueryException if query does not contain downloadable file
  */
  public void getFile(final String query, final File outputFile) throws JsonSyntaxException, IOException, QueryException {
    final PatientSearchRequest request = new PatientSearchRequest();
    request.setQuery(query);
    request.setBinary(false);
    request.setPidCntLimit(Integer.MAX_VALUE);
    request.setReturnPids(true);
    request.setReturnSurvivalData(false);
    request.setReturnTimeIntervals(false);
    request.setStatisticsLimit(0);
    final PatientSearchResponse response = new Gson().fromJson(QueryUtils.query(url + "/" + SEARCH_QUERY, new Gson().toJson(request), -1), PatientSearchResponse.class);
    if (response.containsErrors()) {
      throw new QueryException(response.getErrorMessage());
    }
    if (response.getExportLocation() == null) {
      throw new QueryException("Query does not contain downloadable file");
    }
    final String exportLocation = url + "/" + response.getExportLocation();
    QueryUtils.saveUrlToFile(exportLocation, outputFile);
  }

  /** Returns a patient object containing all the information
   *
   * @param patientId
   * @return
   * @throws JsonSyntaxException
   * @throws IOException
   * @throws QueryException
   */
  public PatientData getPatient(final int patientId) throws JsonSyntaxException, IOException, QueryException {
    final DumpRequest request = DumpRequest.createFull(patientId);
    final DumpResponse response = new Gson().fromJson(QueryUtils.query(url + "/" + DUMP_QUERY, new Gson().toJson(request), -1), DumpResponse.class);
    return PatientData.create(response);
  }

}