package paymaya.com.paymayaandroidcheckout.activities;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.paymaya.sdk.android.payment.PayMayaPayment;
import com.paymaya.sdk.android.payment.models.Card;
import com.paymaya.sdk.android.payment.models.PaymentToken;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractActivity;
import paymaya.com.paymayaandroidcheckout.utils.DateUtils;
import paymaya.com.paymayaandroidcheckout.widgets.MonthYearPickerDialog;

/**
 * Created by jadeantolingaa on 12/4/15.
 */
public class PaymentActivity extends BaseAbstractActivity implements DatePickerDialog
        .OnDateSetListener {
    private static final String CLIENT_KEY = "pk-SjRNZLyr9OmovoHs2dXZ6obTxQ39YsPyc3f7oyrtNCX";

    private PayMayaPayment mPayMayaPayment;
    private Card card;
    private String mUuid;
    private String mMonth;
    private String mYear;

    @Bind(R.id.paymaya_sdk_activity_payment_edit_text_date)
    EditText mEditTextDate;

    @Bind(R.id.paymaya_sdk_activity_payment_edit_text_card_number)
    EditText mEditTextCardNumber;

    @Bind(R.id.paymaya_sdk_activity_payment_edit_text_cvc)
    EditText mEditTextCvc;

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
        Log.d("@onPaymentClick", "Month = " + mMonth
                + " Year = " + mYear
                + " Number = " + number
                + " CVC = " + cvc);
        card = new Card(number, mMonth, mYear, cvc);
        mPayMayaPayment = new PayMayaPayment(CLIENT_KEY, card);

        new AsyncTask<Void, Void, PaymentToken>() {
            @Override
            protected PaymentToken doInBackground(Void... params) {
                return mPayMayaPayment.getPaymentToken();
            }

            @Override
            protected void onPostExecute(PaymentToken paymentToken) {
                Toast.makeText(getApplicationContext(), paymentToken.getPaymentTokenId() +
                                "Date: " + paymentToken.getCreatedAt().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }

    private void showDate() {
        MonthYearPickerDialog pd = new MonthYearPickerDialog();
        pd.setListener(this);
        pd.show(getFragmentManager(), "MonthYearPickerDialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymaya_sdk_activity_payment);
        ButterKnife.bind(this);

        mUuid = UUID.randomUUID().toString();
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mEditTextDate.setText(DateUtils.formatDate(year, monthOfYear, dayOfMonth));

        mMonth = monthOfYear > 10 ? monthOfYear + "" : "0" + monthOfYear;
        mYear = year + "";
    }
}
