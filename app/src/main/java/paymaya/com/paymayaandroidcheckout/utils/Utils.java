package paymaya.com.paymayaandroidcheckout.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class Utils {

    private Utils() {
    }

    public static void loadImage(Context context, int resource, ImageView view) {
        Picasso.with(context).load(resource).fit().centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(view);
    }
}
