package paymaya.com.paymayaandroidcheckout.utils;

import com.paymaya.sdk.android.checkout.models.Item;
import com.paymaya.sdk.android.checkout.models.TotalAmount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.models.ItemModel;

/**
 * Created by jadeantolingaa on 1/13/16.
 */
public class ListItem {

    private ListItem() {
    }
    /**
     *
     * @return List ItemModel
     */
    public static List<ItemModel> getItemModels() {
        /**
         * Create List of ItemModels for Checkout object
         */
        List<ItemModel> mItemModels = new ArrayList<>();

        /**
         * Create ItemModel for List of Items
         *
         * @setmethods - Item ((Item) item)
         *
         */
        ItemModel itemModel = new ItemModel();
        itemModel.setItemName("Bag");
        itemModel.setItemDescription("Black Leather Bag");
        itemModel.setSkuCode("CVG-096731");
        itemModel.setItemPrice(BigDecimal.valueOf(450.99));
        itemModel.setThumbNails(R.mipmap.bag);
        mItemModels.add(itemModel);

        //item2
        itemModel = new ItemModel();
        itemModel.setItemName("Shoe");
        itemModel.setItemDescription("Brown Leather Shoe");
        itemModel.setSkuCode("CVG-096732");
        itemModel.setItemPrice(BigDecimal.valueOf(4500.25));
        itemModel.setThumbNails(R.mipmap.shoe);
        mItemModels.add(itemModel);

        //item3
        itemModel = new ItemModel();
        itemModel.setItemName("Necklace");
        itemModel.setItemDescription("Pricey Necklace");
        itemModel.setSkuCode("CVG-096733");
        itemModel.setItemPrice(BigDecimal.valueOf(8300.25));
        itemModel.setThumbNails(R.mipmap.necklace);
        mItemModels.add(itemModel);

        return mItemModels;
    }
}
