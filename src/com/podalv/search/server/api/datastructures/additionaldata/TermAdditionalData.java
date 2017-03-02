package com.podalv.search.server.api.datastructures.additionaldata;

/** Terms contain note id to which the term in a note belongs to and note type
 *
 * @author podalv
 *
 */
public class TermAdditionalData implements AdditionalData {

  private final String noteType;
  private final int    nodeId;

  public TermAdditionalData(final int noteId, final String noteType) {
    this.nodeId = noteId;
    this.noteType = noteType;
  }

  public int getNodeId() {
    return nodeId;
  }

  public String getNoteType() {
    return noteType;
  }
}
