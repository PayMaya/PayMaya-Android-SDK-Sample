package paymaya.com.paymayaandroidcheckout.utils;

import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by jadeantolingaa on 12/11/15.
 */
public class DateUtils {

    public static CharSequence formatDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, monthOfYear, dayOfMonth);
        return DateFormat.format("MM / yyyy", cal);
    }
}
