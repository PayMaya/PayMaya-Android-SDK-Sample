package paymaya.com.paymayaandroidcheckout.utils;

import android.content.Context;
import android.widget.ImageView;

import com.paymaya.sdk.android.checkout.models.Item;
import com.paymaya.sdk.android.checkout.models.TotalAmount;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.models.ItemModel;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class Utils {

    private Utils() {
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
        //item1

        /**
         * Create TotalAmount per Item
         *
         * @params - (BigDecimal) price, (String) currency
         */
        TotalAmount totalAmount = new TotalAmount(BigDecimal.valueOf(803.78), "PHP");

        /**
         * Create Item for name, count and amount
         *
         * @params - (String) item_name, (int) count, (TotalAmount) total_amount
         * @setmethods - Skucode ((String) code)
         *             - Description ((String) description)
         */
        Item item = new Item("Bag One", 1, totalAmount);
        item.setSkuCode("CVG-096731");
        item.setDescription("bag");

        /**
         * Create ItemModel for List of Items
         *
         * @setmethods - Item ((Item) item)
         *
         */
        ItemModel itemModel = new ItemModel();
        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.bag);
        mItemModels.add(itemModel);

        //item2
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(BigDecimal.valueOf(1945.45), "PHP");
        item = new Item("Shoe One", 1, totalAmount);
        item.setSkuCode("CVG-096732");
        item.setDescription("shoe");

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.shoe);
        mItemModels.add(itemModel);

        //item3
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(BigDecimal.valueOf(5892.34), "PHP");
        item = new Item("Necklace One", 1, totalAmount);
        item.setSkuCode("CVG-096733");
        item.setDescription("necklace");

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.necklace);
        mItemModels.add(itemModel);

        //item4
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(BigDecimal.valueOf(545.56), "PHP");
        item = new Item("Tshirt One", 1, totalAmount);
        item.setSkuCode("CVG-096734");
        item.setDescription("tshirt");

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.tshirt);
        mItemModels.add(itemModel);

        //item5
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(BigDecimal.valueOf(645.67), "PHP");
        item = new Item("Tshirt Two", 1, totalAmount);
        item.setSkuCode("CVG-096735");
        item.setDescription("tshirt");

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.tshirt2);
        mItemModels.add(itemModel);

        //item6
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(BigDecimal.valueOf(1645.03), "PHP");
        item = new Item("Shoe Two", 1, totalAmount);
        item.setSkuCode("CVG-096736");
        item.setDescription("shoe");

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.shoe);
        mItemModels.add(itemModel);

        return mItemModels;
    }

    public static void loadImage(Context context, int resource, ImageView view) {
        Picasso.with(context).load(resource).fit().centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE).into(view);
    }
}
