package com.podalv.search.server.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import com.podalv.search.server.api.responses.DumpResponse;

/** Allows comparing 2 patients and any parts of their data
 * 
 */
public interface CompareUtils {

  static HashMap<Object, ArrayList<Object>> getMissingInLeft(final HashMap<Object, ArrayList<Object>> left, final HashMap<Object, ArrayList<Object>> right) {
    final HashMap<Object, ArrayList<Object>> result = new HashMap<>();
    left.forEach((key, value) -> {
      final ArrayList<Object> rightValue = right.get(key);
      if (rightValue == null) {
        result.put(key, value);
      }
    });
    return result;
  }

  static HashMap<Object, ArrayList<Object>> getDifferentInLeft(final HashMap<Object, ArrayList<Object>> left, final HashMap<Object, ArrayList<Object>> right) {
    final HashMap<Object, ArrayList<Object>> result = new HashMap<>();
    left.forEach((key, value) -> {
      final ArrayList<Object> rightValue = right.get(key);
      if (rightValue != null && !right.get(key).equals(value)) {
        result.put(key, value);
      }
    });
    return result;
  }

  static ComparisonResult compare(final HashMap<Object, ArrayList<Object>> left, final HashMap<Object, ArrayList<Object>> right) {
    final ComparisonResult<HashMap<Object, ArrayList<Object>>> result = new ComparisonResult<>();
    result.setMissingLeft(getMissingInLeft(left, right));
    result.setMissingRight(getMissingInLeft(right, left));
    result.setDifferentLeft(getDifferentInLeft(left, right));
    result.setDifferentRight(getDifferentInLeft(right, left));

    return result.missingLeft().size() == 0 && result.missingRight().size() == 0 && result.differentLeft().size() == 0 && result.differentRight().size() == 0
        ? new IdenticalComparisonResult()
        : result;
  }

  static String explainDiff(final String prefix, final Object o1, final Object o2) {
    return !Objects.equals(o1, o2) ? prefix : "";
  }

  static String explain(final DumpResponse patient1, final DumpResponse patient2) {
    return explainDiff("PID ", patient1.getPatientId(), patient2.getPatientId()) + //
        explainDiff("RECS ", patient1.getRecordStart(), patient2.getRecordStart()) + //
        explainDiff("RECE ", patient1.getRecordEnd(), patient2.getRecordEnd()) + //
        explainDiff("DEATH ", patient1.getDeath(), patient2.getDeath()) + //
        explainDiff("CONTS ", patient1.isContainsStart(), patient2.isContainsStart()) + //
        explainDiff("CONTE ", patient1.isContainsEnd(), patient2.isContainsEnd()) + //
        explainDiff("GEND ", patient1.getGender(), patient2.getGender()) + //
        explainDiff("RAC ", patient1.getRace(), patient2.getRace()) + //
        explainDiff("ETH ", patient1.getEthnicity(), patient2.getEthnicity()) + //
        explainDiff("SELQ ", patient1.getSelectionQuery(), patient2.getSelectionQuery()) + //
        explainDiff("ERR ", patient1.getError(), patient2.getError()) + //
        explainDiff("ICD9 ", patient1.getIcd9(), patient2.getIcd9()) + //
        explainDiff("CPT ", patient1.getCpt(), patient2.getCpt()) + //
        explainDiff("RX ", patient1.getRx(), patient2.getRx()) + //
        explainDiff("SNOMED ", patient1.getSnomed(), patient2.getSnomed()) + //
        explainDiff("NEGT ", patient1.getNegatedTerms(), patient2.getNegatedTerms()) + //
        explainDiff("FAMT ", patient1.getFhTerms(), patient2.getFhTerms()) + //
        explainDiff("POST ", patient1.getPositiveTerms(), patient2.getPositiveTerms()) + //
        explainDiff("VIS ", patient1.getVisitTypes(), patient2.getVisitTypes()) + //      
        explainDiff("NOT ", patient1.getNoteTypes(), patient2.getNoteTypes()) + //
        explainDiff("ATC ", patient1.getAtc(), patient2.getAtc()) + //
        explainDiff("LABS ", patient1.getLabs(), patient2.getLabs()) + //
        explainDiff("LABSR ", patient1.getLabsRaw(), patient2.getLabsRaw()) + //
        explainDiff("VIT ", patient1.getVitals(), patient2.getVitals()) + //          
        explainDiff("ENC ", patient1.getEncounterDays(), patient2.getEncounterDays()) + //
        explainDiff("AGE ", patient1.getAgeRanges(), patient2.getAgeRanges()) + //
        explainDiff("YR ", patient1.getYearRanges(), patient2.getYearRanges());
  }
}
