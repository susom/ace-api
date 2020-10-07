package com.podalv.search.server.api.requests;

/** A request that can ask the server to compress the response
 * 
 */
public interface RequestCompression {

  boolean compressResponse();

}
