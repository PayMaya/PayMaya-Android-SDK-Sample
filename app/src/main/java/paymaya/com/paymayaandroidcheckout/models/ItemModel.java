package paymaya.com.paymayaandroidcheckout.models;


import com.paymaya.checkoutsdkandroid.models.Item;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class ItemModel {
    private Item mItem;
    private int mThumbNails;

    public Item getItem() {
        return mItem;
    }

    public void setItem(Item item) {
        mItem = item;
    }

    public int getThumbNails() {
        return mThumbNails;
    }

    public void setThumbNails(int thumbNails) {
        mThumbNails = thumbNails;
    }
}
