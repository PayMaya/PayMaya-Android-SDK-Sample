package paymaya.com.paymayaandroidcheckout.models;


import com.paymaya.sdk.android.checkout.models.Item;

import java.math.BigDecimal;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class ItemModel {
    private int mThumbNails;
    private BigDecimal mItemPrice;
    private String mItemName;
    private String mItemDescription;
    private String mSkuCode;

    public int getThumbNails() {
        return mThumbNails;
    }

    public void setThumbNails(int thumbNails) {
        mThumbNails = thumbNails;
    }

    public BigDecimal getItemPrice() {
        return mItemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        mItemPrice = itemPrice;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public String getItemDescription() {
        return mItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        mItemDescription = itemDescription;
    }

    public String getSkuCode() {
        return mSkuCode;
    }

    public void setSkuCode(String skuCode) {
        mSkuCode = skuCode;
    }
}
