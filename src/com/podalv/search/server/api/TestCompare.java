package com.podalv.search.server.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import com.podalv.search.server.api.exceptions.QueryException;
import com.podalv.search.server.api.responses.DumpResponse;

public class TestCompare {

  public static void main(final String[] args) throws IOException, QueryException {
    final AtlasConnection atlas1 = new AtlasConnection("http://35.233.161.252:8080");
    final AtlasConnection atlas2 = new AtlasConnection("http://35.233.161.252:8081");

    assert atlas1.test() && atlas2.test();

    final int maxPatientCnt = 2000;

    final LinkedList<Integer> pids = atlas1.getPatientIds(maxPatientCnt);
    for (final Integer pid : pids) {
      if (!atlas1.containsPatient(pid)) {
        System.out.println("NEW is missing PID " + pid);
      }
      if (atlas1.containsPatient(pid) && atlas2.containsPatient(pid)) {
        final DumpResponse p1 = atlas1.getPatientDumpResponse(pid);
        final DumpResponse p2 = atlas2.getPatientDumpResponse(pid);
        if (!p1.equals(p2)) {
          if (pid == 9235120) {
            final ComparisonResult result = CompareUtils.compare((HashMap) p1.getVitals(), (HashMap) p1.getVitals(), 2);
            System.out.println("MISSING ORIG: " + result.missingLeft());
            System.out.println("MISSING NEW : " + result.missingRight());
            System.out.println("DIFF ORIG   : " + result.differentLeft());
            System.out.println("DIFF NEW    : " + result.differentRight());
            //break;
          }
          System.out.println(pid + " = " + CompareUtils.explain(p1, p2));
        }
        else {
          System.out.println("SAME for " + pid);
        }
      }
    }

  }
}
