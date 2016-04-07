package paymaya.com.paymayaandroidcheckout.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jadeantolingaa on 2/23/16.
 */
public class AndroidClient implements Client {
    private static final String TAG = "AndroidClient";

    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    @Override
    public Response call(Request request) {
        HttpURLConnection conn = initializeHttpConnection(request);
        try {
            switch (request.getMethod()) {
                case GET:
                    conn.setRequestMethod("GET");
                    break;
                case POST:
                    conn.setRequestMethod("POST");
                    break;
                default:
                    throw new RuntimeException("Invalid method " + request.getMethod());
            }
            if (request.getBody() != null) {
                conn.setDoOutput(true);
                write(conn.getOutputStream(), request.getBody());
            }
            int code = conn.getResponseCode();
            String message = conn.getResponseMessage();
            String response;
            if (code < 200 || code > 299) {
                response = read(conn.getErrorStream());
            } else {
                response = read(conn.getInputStream());
            }

            Log.d(TAG, "Status: " + code + " " + message);
            Log.d(TAG, "response: " + response);
            Log.d(TAG, "Response: " + new JSONObject(response).toString(2));
            return new Response(code, response);
        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getMessage());
            return new Response(-1, "error: " + e.getMessage());
        } finally {
            conn.disconnect();
        }
    }

    private HttpURLConnection initializeHttpConnection(Request request) {
        try {
            HttpURLConnection conn = (HttpURLConnection) request.getUrl().openConnection();
            if (conn instanceof HttpsURLConnection) {
                try {
                    SSLHelper.injectSSLSocketFactory((HttpsURLConnection) conn, SSLHelper.PROTOCOL_TLS_V_1_2);
                } catch (NoSuchAlgorithmException | KeyManagementException ignored) {
                    Log.w(TAG, "TLS V1.2 is not supported.");
                }
            }
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);
            if (request.getHeaders() != null) {
                for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            return conn;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            throw new RuntimeException("Invalid URL: " + request.getUrl());
        }
    }

    private String read(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String data;
        while ((data = br.readLine()) != null) {
            response.append(data);
        }
        is.close();
        return response.toString();
    }

    private void write(OutputStream os, byte[] body) throws IOException {
        DataOutputStream out = new DataOutputStream(os);
        out.write(body);
        out.flush();
        out.close();
    }
}
