package paymaya.com.paymayaandroidcheckout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractActivity;

/**
 * Created by jadeantolingaa on 1/19/16.
 */
public class SdkActivity extends BaseAbstractActivity {

    @OnClick(R.id.paymaya_sdk_activity_button_checkout)
    public void onButtonCheckoutClick() {
        startActivity(new Intent(this, CheckoutActivity.class));
    }

    @OnClick(R.id.paymaya_sdk_activity_button_payment)
    public void onButtonPaymentClick() {
        startActivity(new Intent(this, PaymentActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymaya_sdk_activity);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }
}
