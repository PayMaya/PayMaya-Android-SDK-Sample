package paymaya.com.paymayaandroidcheckout.request;

import java.net.HttpURLConnection;

/**
 * Created by jadeantolingaa on 2/23/16.
 */
public interface Client {

    Response call(Request request);
}
