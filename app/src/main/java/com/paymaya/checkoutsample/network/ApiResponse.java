package com.paymaya.checkoutsample.network;

/**
 * Created by samfrancisco on 10/1/15.
 */
public class ApiResponse {

  private int mResponseCode;
  private String mResponseBody;

  public ApiResponse(int responseCode, String responseBody) {
    mResponseCode = responseCode;
    mResponseBody = responseBody;
  }

  public int getResponseCode() {
    return mResponseCode;
  }

  public String getResponseBody() {
    return mResponseBody;
  }

}
