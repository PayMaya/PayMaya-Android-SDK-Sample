package paymaya.com.paymayaandroidcheckout.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.paymaya_sdk_android.payment.PayMayaPayment;
import com.paymaya_sdk_android.payment.models.Card;
import com.paymaya_sdk_android.payment.models.PaymentToken;

import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractActivity;

/**
 * Created by jadeantolingaa on 12/4/15.
 */
public class PaymentActivity extends BaseAbstractActivity{
    private static final String CLIENT_KEY = "pk-SjRNZLyr9OmovoHs2dXZ6obTxQ39YsPyc3f7oyrtNCX";

    private PayMayaPayment mPayMayaPayment;
    private Card card;
    private String mUuid;

    @OnClick(R.id.paymaya_sdk_activity_payment_button_pay)
    public void onPaymentButtonClicked() {
        String number = "4242424242424242";
        String expMonth = "12";
        String expYear = "2015";
        String cvc = "123";
        card = new Card(number, expMonth, expYear, cvc);

        new AsyncTask<Void, Void, PaymentToken>() {
            @Override
            protected PaymentToken doInBackground(Void... params) {
                return mPayMayaPayment.getPaymentToken(card, mUuid);
            }

            @Override
            protected void onPostExecute(PaymentToken paymentToken) {
                Toast.makeText(getApplicationContext(), paymentToken.getId(), Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymaya_sdk_activity_payment);
        ButterKnife.bind(this);
        mPayMayaPayment = new PayMayaPayment(CLIENT_KEY);
        mUuid = UUID.randomUUID().toString();
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }
}
