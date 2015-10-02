package com.paymaya.checkoutsample.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paymaya.checkoutsample.R;
import com.paymaya.checkoutsample.models.CheckoutPayload;
import com.paymaya.checkoutsample.network.ApiRequest;
import com.paymaya.checkoutsample.network.ApiResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  private static final String CHECKOUT_API_URL = "https://sandbox-checkout-api.paymaya.com/v1/checkouts";
  private static final String CLIENT_KEY = "8510f691-8c0b-4f28-bfa0-bcced0cb0fd2";
  private static final String CLIENT_SECRET = "";

  private static final long PRODUCT_ID = 6319921;
  private static final String SUCCESS_URL = "http://shop.someserver.com/success?id=" + PRODUCT_ID;
  private static final String FAILURE_URL = "http://shop.someserver.com/failure?id=" + PRODUCT_ID;
  private static final String CANCEL_URL = "http://shop.someserver.com/cancel?id=" + PRODUCT_ID;

  private static final int CHECKOUT_REQUEST_CODE = 1234;

  private Button checkoutButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initializeViews();
  }

  private void initializeViews() {
    checkoutButton = (Button) findViewById(R.id.activity_main_button_checkout);
    checkoutButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        CheckoutPayload checkoutPayload = new CheckoutPayload();
        try {
          checkoutPayload.generateSamplePayload(SUCCESS_URL, FAILURE_URL, CANCEL_URL);
          new CheckoutTask().execute(checkoutPayload, null, null);
        }
        catch( JSONException e ) {
          Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
      }

    });
  }

  private class CheckoutTask extends AsyncTask<CheckoutPayload, Void, ApiResponse> {


    @Override
    protected ApiResponse doInBackground(CheckoutPayload... checkoutPayloads) {
      CheckoutPayload checkoutPayload = checkoutPayloads[0];
      Log.i(TAG, "checkoutPayload");
      Log.d(TAG, checkoutPayload.toString());

      String clientCredentials = CLIENT_KEY + ":" + CLIENT_SECRET;
      String encodedCredentials = Base64.encodeToString(clientCredentials.getBytes(), Base64.DEFAULT);
      Log.i(TAG, "encodedCredentials");
      Log.d(TAG, "" + encodedCredentials);

      Map<String, String> requestHeaders = new HashMap<String, String>();
      requestHeaders.put("Content-Type", "application/json");
      requestHeaders.put("Authorization", "Basic " + encodedCredentials);
      Log.i(TAG, "content-length : " + checkoutPayload.getPayload().toString().getBytes().length);
      Log.i(TAG, "content-length : " + Integer.toString(checkoutPayload.getPayload().toString().getBytes().length));
      requestHeaders.put("Content-Length", Integer.toString(checkoutPayload.getPayload().toString().getBytes().length));

      ApiRequest apiRequest = new ApiRequest();

      try {
        return apiRequest.post(CHECKOUT_API_URL, requestHeaders, checkoutPayload.getPayload());
      }
      catch( MalformedURLException e ) {
        Log.e(TAG, e.getMessage());
      }
      catch( IOException e ) {
        Log.e(TAG, e.getMessage());
      }
      catch( NoSuchAlgorithmException e ) {
        Log.e(TAG, e.getMessage());
      }
      catch( KeyManagementException e ) {
        Log.e(TAG, e.getMessage());
      }

      return null;
    }

    @Override
    protected void onPostExecute(ApiResponse apiResponse) {
      Log.i(TAG, "apiResponse");

      if (null == apiResponse) {
        Log.e(TAG, "no API Response");
        return;
      }

      Log.d(TAG, "responseCode : " + apiResponse.getResponseCode());
      Log.d(TAG, "responseBody : " + apiResponse.getResponseBody());

      try {
        JSONObject responseObject = new JSONObject(apiResponse.getResponseBody());
        String redirectUrl = responseObject.getString("redirectUrl");

        final Intent checkoutWebActivityIntent = new Intent(MainActivity.this, PayMayaCheckoutWebViewActivity.class);

        /**
         * Pass the URLs that we will monitor in the WebView
         */
        checkoutWebActivityIntent.putExtra(PayMayaCheckoutWebViewActivity.KEY_REDIRECT_URL, redirectUrl);
        checkoutWebActivityIntent.putExtra(PayMayaCheckoutWebViewActivity.KEY_SUCCESS_URL, SUCCESS_URL);
        checkoutWebActivityIntent.putExtra(PayMayaCheckoutWebViewActivity.KEY_FAILURE_URL, FAILURE_URL);
        checkoutWebActivityIntent.putExtra(PayMayaCheckoutWebViewActivity.KEY_CANCEL_URL, CANCEL_URL);

        startActivityForResult(checkoutWebActivityIntent, CHECKOUT_REQUEST_CODE);
      }
      catch(JSONException e) {
        Log.e(TAG, ""+e.getMessage());
        Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
      }

      super.onPostExecute(apiResponse);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode != CHECKOUT_REQUEST_CODE) {
      return;
    }

    if (resultCode == RESULT_OK) {
      Toast.makeText(MainActivity.this, "Result OK", Toast.LENGTH_SHORT).show();
      return;
    }

    if (resultCode == RESULT_CANCELED) {
      Toast.makeText(MainActivity.this, "Result Canceled", Toast.LENGTH_SHORT).show();
      return;
    }

    if (resultCode == PayMayaCheckoutWebViewActivity.RESULT_FAILURE) {
      Toast.makeText(MainActivity.this, "Result Failure", Toast.LENGTH_SHORT).show();
      return;
    }
  }
}
