package com.podalv.search.server.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class QueryUtils {

  public static String query(final String slaveUrl, final String query, final int queryId, final int timeout) throws IOException {
    final StringBuilder result = new StringBuilder();
    if (slaveUrl == null) {
      return null;
    }
    else {
      final URL url = new URL(slaveUrl);
      final HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
      urlc.setConnectTimeout(timeout);
      urlc.setReadTimeout(timeout);
      urlc.setDoOutput(true);
      urlc.setRequestMethod("POST");
      urlc.setAllowUserInteraction(false);
      urlc.setReadTimeout(0);
      final PrintStream ps = new PrintStream(urlc.getOutputStream());
      ps.print(query);
      ps.close();

      final BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
      String l = null;
      while ((l = br.readLine()) != null) {
        result.append(l + "\n");
      }
      br.close();
    }
    return result.toString();
  }

  public static String query(final String slaveUrl, final String query, final int queryId) throws IOException {
    return query(slaveUrl, query, queryId, 0);
  }

  public static void saveUrlToFile(final String url, final File outputFile) throws IOException {
    final URL website = new URL(url);
    final ReadableByteChannel rbc = Channels.newChannel(website.openStream());
    final FileOutputStream fos = new FileOutputStream(outputFile);
    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    fos.close();
  }
}
