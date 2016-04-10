package paymaya.com.paymayaandroidcheckout.activities;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.paymaya.sdk.android.PayMayaConfig;
import com.paymaya.sdk.android.checkout.models.Buyer;
import com.paymaya.sdk.android.checkout.models.TotalAmount;
import com.paymaya.sdk.android.payment.PayMayaPayment;
import com.paymaya.sdk.android.payment.PayMayaPaymentException;
import com.paymaya.sdk.android.payment.models.Card;
import com.paymaya.sdk.android.payment.models.PaymentToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractActivity;
import paymaya.com.paymayaandroidcheckout.models.Payments;
import paymaya.com.paymayaandroidcheckout.request.AndroidClient;
import paymaya.com.paymayaandroidcheckout.request.Request;
import paymaya.com.paymayaandroidcheckout.request.Response;
import paymaya.com.paymayaandroidcheckout.utils.DateUtils;
import paymaya.com.paymayaandroidcheckout.utils.JsonUtils;
import paymaya.com.paymayaandroidcheckout.widgets.MonthYearPickerDialog;

/**
 * Created by jadeantolingaa on 12/4/15.
 */
public class PaymentActivity extends BaseAbstractActivity implements DatePickerDialog
        .OnDateSetListener {

    public static final String BUNDLE_PAYMENT_TOTAL_AMOUNT = "total_amount";
    public static final String BUNDLE_PAYMENT_BUYER = "buyer";
    public static final String EXTRAS_BUNDLE_PAYMENT = "payment";

    private static final int MY_SCAN_REQUEST_CODE = 100;
    private static final String CLIENT_KEY = "pk-N6TvoB4GP2kIgNz4OCchCTKYvY5kPQd2HDRSg8rPeQG";
    // To test expired key, use the following:
    // private static final String CLIENT_KEY = "pk-OKkXqYUN1bkzgstdCRqJ6hlmzLUNYq6koeKBFVNxY7E";

    private static final String MERCHANT_BACKEND_URL = "http://52.77.55.105/payments";

    private PayMayaPayment mPayMayaPayment;
    private Card card;
    private String mUuid;
    private String mMonth;
    private String mYear;

    private Buyer mBuyer;
    private TotalAmount mTotalAmount;

    @Bind(R.id.paymaya_sdk_activity_payment_edit_text_date)
    EditText mEditTextDate;

    @Bind(R.id.paymaya_sdk_activity_payment_edit_text_card_number)
    EditText mEditTextCardNumber;

    @Bind(R.id.paymaya_sdk_activity_payment_edit_text_cvc)
    EditText mEditTextCvc;

    @Bind(R.id.paymaya_sdk_activity_payment_edit_text_payment_token)
    EditText mEditTextPaymentToken;

    @Bind(R.id.paymaya_sdk_activity_payment_button_copy_to_clipboard)
    Button mButtonCopyToClipboard;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymaya_sdk_activity_payment);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(EXTRAS_BUNDLE_PAYMENT);

        mBuyer = bundle.getParcelable(BUNDLE_PAYMENT_BUYER);
        mTotalAmount = bundle.getParcelable(BUNDLE_PAYMENT_TOTAL_AMOUNT);

        Log.i("SAMTEST", "" + PayMayaConfig.getEnvironment());
        mUuid = UUID.randomUUID().toString();

        hidePaymentToken();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @OnClick(R.id.paymaya_sdk_activity_payment_button_scan_card)
    public void onButtonScanCardClick() {
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true);

        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);


    }

    @OnClick(R.id.paymaya_sdk_activity_payment_edit_text_date)
    public void onDateClicked() {
        showDate();
    }

    @OnFocusChange(R.id.paymaya_sdk_activity_payment_edit_text_date)
    public void onDateFocusChanged(View view, boolean hasFocus) {
        if (hasFocus) {
            showDate();
        }
    }

    @OnClick(R.id.paymaya_sdk_activity_payment_button_pay)
    public void onPaymentButtonClicked() {
        String number = mEditTextCardNumber.getText().toString().trim();
        String cvc = mEditTextCvc.getText().toString().trim();
        Log.i("@onPaymentClick", "Month = " + mMonth
                + " Year = " + mYear
                + " Number = " + number
                + " CVC = " + cvc);
        card = new Card(number, mMonth, mYear, cvc);
        mPayMayaPayment = new PayMayaPayment(CLIENT_KEY, card);

        new AsyncTask<Void, Void, PaymentToken>() {
            private String exceptionMessage;

            @Override
            protected PaymentToken doInBackground(Void... params) {
                try {
                    Log.i("samtest", "mPayMayaPayment: " + mPayMayaPayment);
                    return mPayMayaPayment.getPaymentToken();
                } catch (PayMayaPaymentException e) {
                    exceptionMessage = e.getMessage();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(PaymentToken paymentToken) {
                if (null == exceptionMessage) {
                    final String paymentTokenId = paymentToken.getPaymentTokenId();
                    mEditTextPaymentToken.setText(paymentTokenId);
                    showPaymentToken();
                    new PaymentsTask().execute(paymentTokenId);
                    return;
                }

                Toast.makeText(getApplicationContext(), "Error: " + exceptionMessage,
                        Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        mClient.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Payment Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://paymaya.com.paymayaandroidcheckout" +
//                        ".activities/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Payment Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://paymaya.com.paymayaandroidcheckout.activities/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(mClient, viewAction);
//        mClient.disconnect();
    }

    private class PaymentsTask extends AsyncTask<String, Void, Payments> {

        @Override
        protected Payments doInBackground(String... params) {
            try {
                String paymentTokenId = params[0];

                URL url = new URL(MERCHANT_BACKEND_URL);
                Request request = new Request(Request.Method.POST, url);

                JSONObject totalAmountObject = new JSONObject();
                totalAmountObject.put("amount", mTotalAmount.getValue());
                totalAmountObject.put("currency", mTotalAmount.getCurrency());

                JSONObject contactObject = new JSONObject();
                contactObject.put("phone", mBuyer.getContact().getPhone());
                contactObject.put("email", mBuyer.getContact().getEmail());

                JSONObject billingAddressObject = new JSONObject();
                billingAddressObject.put("line1", mBuyer.getBillingAddress().getLine1());
                billingAddressObject.put("line2", mBuyer.getBillingAddress().getLine2());
                billingAddressObject.put("city", mBuyer.getBillingAddress().getCity());
                billingAddressObject.put("state", mBuyer.getBillingAddress().getState());
                billingAddressObject.put("zipCode", mBuyer.getBillingAddress().getZipCode());
                billingAddressObject.put("countryCode", mBuyer.getBillingAddress().getCountryCode());

                JSONObject buyerObject = new JSONObject();
                buyerObject.put("firstName", mBuyer.getFirstName());
                buyerObject.put("middleName", mBuyer.getMiddleName());
                buyerObject.put("lastName", mBuyer.getLastName());
                buyerObject.put("contact", contactObject);
                buyerObject.put("billingAddress", billingAddressObject);

                JSONObject parentRoot = new JSONObject();
                parentRoot.put("paymentToken", paymentTokenId);
                parentRoot.put("totalAmount", totalAmountObject);
                parentRoot.put("buyer", buyerObject);

                Log.i("samtest", "parentRoot: " + parentRoot);

                byte[] body = parentRoot.toString().getBytes();
                request.setBody(body);

                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                // headers.put("Authorization", "Bearer jCAoFKxG4U5Ez9gZj3jxOnyMKNlMDGg9pO/SUlkJNeiEOLCCx5g5Sv478G5fysou");
                // headers.put("Authorization", "Bearer K2cLQIbNrO9S/QQspectTYHx9tNFD/m0CGA5GNQWLVx+7e2TsqnynkyOet+yz4kF");
                headers.put("Authorization", "Bearer 3BI4dTaewiyfJGcc9Fzg+r2MM1qSc80LcRqxVpZTIoaRb2uIQ1SSRtfQWEsHeJud");

                request.setHeaders(headers);

                AndroidClient androidClient = new AndroidClient();
                Response response = androidClient.call(request);

                Log.i("samtest", "response : " + response.toString());
                Log.i("samtest", "response.getCode() : " + response.getCode());
                Log.i("samtest", "response.getResponse() : " + response.getResponse());

                return JsonUtils.fromJSONPayments(response.getResponse());
            } catch (JSONException je) {
                Log.e("samtest", "je: " + je.getMessage());
                return null;
            } catch (PayMayaPaymentException ppe) {
                Log.e("samtest", "ppe: " + ppe.getMessage());
                return null;
            } catch (MalformedURLException mue) {
                Log.e("samtest", "mue: " + mue.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(Payments payments) {
            Log.i("samtest", "payments : " + payments);
            if (payments != null) {
                Toast.makeText(getApplicationContext(), "Status: " + payments.getStatus(), Toast
                        .LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.paymaya_sdk_activity_payment_button_copy_to_clipboard)
    public void onCopyToClipboardButtonClicked() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("PaymentToken",
                mEditTextPaymentToken.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(getApplicationContext(), "Payment Token copied to clipboard.",
                Toast.LENGTH_SHORT).show();
    }

    private void showPaymentToken() {
        Log.d("@showPayment", "visible");
        mEditTextPaymentToken.setVisibility(View.VISIBLE);
        mButtonCopyToClipboard.setVisibility(View.VISIBLE);
    }

    private void hidePaymentToken() {
        mEditTextPaymentToken.setVisibility(View.INVISIBLE);
        mButtonCopyToClipboard.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mEditTextDate.setText(DateUtils.formatDate(year, monthOfYear, dayOfMonth));

        mMonth = monthOfYear > 10 ? monthOfYear + "" : "0" + monthOfYear;
        mYear = year + "";
    }

    private void showDate() {
        MonthYearPickerDialog pd = new MonthYearPickerDialog();
        pd.setListener(this);
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {

            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                mEditTextCardNumber.setText(scanResult.getFormattedCardNumber());

                if (scanResult.isExpiryValid()) {
                    mEditTextDate.setText(scanResult.expiryMonth + "/" + scanResult.expiryYear);
                }

                if (null != scanResult.cvv) {
                    mEditTextCvc.setText(scanResult.cvv);
                }
            }
        }
    }
}
