package paymaya.com.paymayaandroidcheckout;

import android.app.Application;

import com.paymaya.sdk.android.PayMayaConfig;
import com.paymaya.sdk.android.checkout.models.Item;
import com.paymaya.sdk.android.checkout.models.TotalAmount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.models.Product;
import paymaya.com.paymayaandroidcheckout.utils.ListItem;

/**
 * Created by jadeantolingaa on 1/6/16.
 */
public class SampleApplication extends Application {
    private static final int CONFIG_ENVIRONMENT = PayMayaConfig.ENVIRONMENT_SANDBOX;

    private List<Product> mProductList = new ArrayList<>();
    private HashMap<Integer, Integer> mHashMap = new HashMap<Integer, Integer>();
    private BigDecimal mShippingFee = new BigDecimal("0");
    private BigDecimal mTax = new BigDecimal("0");
    private BigDecimal mExtra = new BigDecimal("0");
    private BigDecimal mSubtotal = new BigDecimal("0");

    @Override
    public void onCreate() {
        super.onCreate();
        PayMayaConfig.setEnvironment(CONFIG_ENVIRONMENT);
        mProductList = ListItem.getItemModels();
    }

    public List<Product> getProductList() {
        return mProductList;
    }

    public HashMap<Integer, Integer> getHashMap() {
        return mHashMap;
    }

    public void setHashMap(HashMap<Integer, Integer> hashMap) {
        mHashMap = hashMap;
    }

    public BigDecimal getShippingFee() {
        return mShippingFee;
    }

    public BigDecimal getTax() {
        return mTax;
    }

    public BigDecimal getExtra() {
        return mExtra;
    }

    public BigDecimal getSubtotal() {
        return mSubtotal;
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal("0");

        if (!mHashMap.isEmpty()) {
            for (int i = 0; i <= mHashMap.size(); i++) {
                if (mHashMap.get(i) != null) {
                    if (mHashMap.get(i) != 0) {
                        int quantity = mHashMap.get(i);
                        mSubtotal = mProductList.get(i).getItemPrice().multiply(BigDecimal.valueOf
                                (quantity));
                    }
                }
            }
            mShippingFee = mSubtotal.multiply(BigDecimal.valueOf(0.05));
            mTax = mSubtotal.multiply(BigDecimal.valueOf(0.12));

            mExtra = mShippingFee.add(mTax);
            total = mSubtotal.add(mExtra);
        }
        return total.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public List<Item> getItems() {
        List<Item> mItems = new ArrayList<>();
        Item item;

        for (int i = 0; i <= mProductList.size(); i++) {
            if (!mHashMap.isEmpty()) {
                if (mHashMap.get(i) != null) {
                    if (mHashMap.get(i) != 0) {
                        int quantity = mHashMap.get(i);
                        TotalAmount amount = new TotalAmount(mProductList.get(i).getItemPrice()
                                .multiply(BigDecimal.valueOf(quantity)), "PHP");
                        item = new Item(mProductList.get(i).getItemName(), quantity, amount);
                        mItems.add(item);
                    }
                }
            }
        }

        return mItems;
    }
}
