package paymaya.com.paymayaandroidcheckout.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class Utils {

    private Utils() {
    }

    public static void loadImage(Context context, String url, ImageView view) {
        Picasso.with(context).invalidate(url);
        Picasso.with(context).load(url).fit().centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(view);
    }

    public static void loadImage(Context context, int resource, ImageView view) {
        Picasso.with(context).load(resource).fit().centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(view);
    }

    public static void loadImage(Context context, File file, ImageView view) {
        Picasso.with(context).setLoggingEnabled(true);
        Picasso.with(context).load(file).fit().centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(view);
    }
}
