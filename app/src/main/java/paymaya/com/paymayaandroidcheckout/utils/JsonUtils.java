package paymaya.com.paymayaandroidcheckout.utils;

import android.util.Log;

import com.paymaya.sdk.android.payment.PayMayaPaymentException;
import com.paymaya.sdk.android.payment.models.PaymentToken;

import org.json.JSONException;
import org.json.JSONObject;

import paymaya.com.paymayaandroidcheckout.models.Payments;

/**
 * Created by jadeantolingaa on 2/23/16.
 */
public class JsonUtils {

    public static Payments fromJSONPayments(String json) throws JSONException {
        JSONObject root = new JSONObject(json);

        Payments payments = new Payments();
        payments.setId(root.getString("id"));
        payments.setEnvironment(root.getString("environment"));
        payments.setStatus(root.getString("status"));
        payments.setPaymentTokenId(root.getString("paymentTokenId"));
        payments.setDescription(root.getString("description"));

        return payments;
    }
}
