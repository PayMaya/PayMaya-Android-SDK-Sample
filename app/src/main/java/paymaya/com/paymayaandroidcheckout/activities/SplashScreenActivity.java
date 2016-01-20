package paymaya.com.paymayaandroidcheckout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractActivity;

/**
 * Created by jadeantolingaa on 1/20/16.
 */
public class SplashScreenActivity extends BaseAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymaya_sdk_activity_splash_screen);
        goToSdkActivity();
    }

    private void goToSdkActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, SdkActivity.class));
                finish();
            }
        }, 1000);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }
}
