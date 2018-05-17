package com.podalv.search.server.api.responses;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

/** Responses that can produce either a binary or a standard JSON response
 *
 * @author podalv
 *
 */
public abstract class SerializableResponse implements Closeable {

  @Expose(serialize = false) @JsonIgnore final transient ByteArrayOutputStream bos            = new ByteArrayOutputStream();
  @Expose(serialize = false) @JsonIgnore final transient DataOutputStream      dos            = new DataOutputStream(bos);
  @JsonProperty("binaryResponse") protected boolean                            binaryResponse = false;

  public ByteArrayOutputStream getByteOutput() {
    return bos;
  }

  public DataOutputStream getDataOutput() {
    return dos;
  }

  protected void writeBinary(final OutputStream out) throws IOException {
    bos.writeTo(out);
    out.flush();
  }

  public void setBinaryResponse(final boolean binaryResponse) {
    this.binaryResponse = binaryResponse;
  }

  public void write(final OutputStream out) throws IOException {
    if (binaryResponse) {
      writeBinary(out);
    }
    else {
      final PrintWriter pw = new PrintWriter(out);
      pw.println(new Gson().toJson(this));
      pw.flush();
    }
  }

}
