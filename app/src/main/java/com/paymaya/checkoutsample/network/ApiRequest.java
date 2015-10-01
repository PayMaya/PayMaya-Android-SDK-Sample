package com.paymaya.checkoutsample.network;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;

/**
 * Created by samfrancisco on 10/1/15.
 */
public class ApiRequest {

  public ApiResponse post(String url, Map<String, String> requestHeaders, JSONObject requestBody)
          throws MalformedURLException, IOException, NoSuchAlgorithmException, KeyManagementException {
    HttpsURLConnection connection = null;
    String payload = requestBody.toString();

    connection = (HttpsURLConnection) new URL(url).openConnection();
    PayMayaSSLHelper.injectSSLSocketFactory(connection, PayMayaSSLHelper.PROTOCOL_TLS_V_1_2);

    connection.setReadTimeout(10000);
    connection.setConnectTimeout(15000);
    connection.setRequestMethod("POST");
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setUseCaches(false);
    connection.setFixedLengthStreamingMode(payload.getBytes().length);

    for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
      connection.setRequestProperty(entry.getKey(), entry.getValue());
    }

    /*
    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
    out.writeBytes(URLEncoder.encode(requestBody.toString(), "UTF-8"));
    out.flush();
    out.close();
    */

    connection.connect();

    OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
    outputStream.write(payload.getBytes());
    outputStream.flush();

    return parseResponse(connection);
  }

  private ApiResponse parseResponse(HttpsURLConnection connection)
          throws IOException {
    int responseCode = connection.getResponseCode();
    Log.i("ApiRequest", "parseResponse");
    Log.d("ApiRequest", "responseCode : " + responseCode);

    String responseBody = null;

    switch(responseCode) {
      case HTTP_OK: case HTTP_CREATED: case HTTP_ACCEPTED: {
        responseBody = readStream(connection.getInputStream());
        return new ApiResponse(responseCode, responseBody);
      }

      case HTTP_UNAUTHORIZED:
      case HTTP_FORBIDDEN:
      case HTTP_INTERNAL_ERROR:
      case HTTP_UNAVAILABLE: {
        // do something here when not HTTP_OK
        Log.i("ApiRequest", "not HTTP_OK");
        responseBody = readStream(connection.getInputStream());
        return new ApiResponse(responseCode, responseBody);
      }

      default: {
        // handle other cases
        Log.i("ApiRequest", "default");
        responseBody = readStream(connection.getInputStream());
        return new ApiResponse(responseCode, responseBody);
      }
    }
  }

  private String readStream(InputStream in) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    for (int count; (count = in.read(buffer)) != -1; ) {
      out.write(buffer, 0, count);
    }

    return new String(out.toByteArray(), "UTF-8");
  }

}
