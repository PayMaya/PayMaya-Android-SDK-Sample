package paymaya.com.paymayaandroidcheckout;

import android.app.Application;

import com.paymaya.sdk.android.PayMaya;

/**
 * Created by jadeantolingaa on 1/6/16.
 */
public class SampleApplication extends Application {
    private static final int CONFIG_ENVIRONMENT = PayMaya.ENVIRONMENT_SANDBOX;

    @Override
    public void onCreate() {
        super.onCreate();
        PayMaya.init(CONFIG_ENVIRONMENT);
    }


}
