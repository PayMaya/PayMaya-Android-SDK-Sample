package paymaya.com.paymayaandroidcheckout;

import android.app.Application;

import com.paymaya.sdk.android.PayMaya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.models.ItemModel;
import paymaya.com.paymayaandroidcheckout.utils.ListItem;

/**
 * Created by jadeantolingaa on 1/6/16.
 */
public class SampleApplication extends Application {
    private static final int CONFIG_ENVIRONMENT = PayMaya.ENVIRONMENT_SANDBOX;

    private List<ItemModel> mItemModelList = new ArrayList<>();
    private HashMap<Integer, Integer> mHashMap = new HashMap<Integer, Integer>();

    @Override
    public void onCreate() {
        super.onCreate();
        PayMaya.init(CONFIG_ENVIRONMENT);
        mItemModelList = ListItem.getItemModels();
    }

    public List<ItemModel> getItemModelList() {
        return mItemModelList;
    }

    public HashMap<Integer, Integer> getHashMap() {
        return mHashMap;
    }

    public void setHashMap(HashMap<Integer, Integer> hashMap) {
        mHashMap = hashMap;
    }
}
