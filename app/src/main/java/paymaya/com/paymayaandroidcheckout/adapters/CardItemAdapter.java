package paymaya.com.paymayaandroidcheckout.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Item;
import com.voyagerinnovation.paymaya_sdk_android_checkout.models.TotalAmount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.models.ItemModel;
import paymaya.com.paymayaandroidcheckout.utils.Utils;
import paymaya.com.paymayaandroidcheckout.widgets.CardItemViewHolder;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class CardItemAdapter extends BaseAdapter {

    private List<ItemModel> mItemModels;
    private Context mContext;

    public CardItemAdapter(Context context) {
        super();
        mContext = context;
        mItemModels = new ArrayList<ItemModel>();
        //item1
        ItemModel itemModel = new ItemModel();
        TotalAmount totalAmount = new TotalAmount(new BigDecimal(803), "PHP");
        Item item = new Item("Bag One", new BigDecimal(1), totalAmount);

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.bag);
        mItemModels.add(itemModel);

        //item2
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(new BigDecimal(1945), "PHP");
        item = new Item("Shoe One", new BigDecimal(1), totalAmount);

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.shoe);
        mItemModels.add(itemModel);

        //item3
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(new BigDecimal(5892), "PHP");
        item = new Item("Necklace One", new BigDecimal(1), totalAmount);

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.necklace);
        mItemModels.add(itemModel);

        //item4
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(new BigDecimal(545), "PHP");
        item = new Item("Tshirt One", new BigDecimal(1), totalAmount);

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.tshirt);
        mItemModels.add(itemModel);

        //item5
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(new BigDecimal(645), "PHP");
        item = new Item("Tshirt Two", new BigDecimal(1), totalAmount);

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.tshirt2);
        mItemModels.add(itemModel);

        //item6
        itemModel = new ItemModel();
        totalAmount = new TotalAmount(new BigDecimal(1645), "PHP");
        item = new Item("Shoe Two", new BigDecimal(1), totalAmount);

        itemModel.setItem(item);
        itemModel.setThumbNails(R.mipmap.shoe);
        mItemModels.add(itemModel);
    }

    @Override
    public int getCount() {
        return mItemModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        CardItemViewHolder holder;
        ItemModel itemModel = mItemModels.get(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.paymaya_checkout_image_list_item, parent, false);
            holder = new CardItemViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CardItemViewHolder) view.getTag();
        }
        Log.d("@Adapter", "image = " + itemModel.getThumbNails());
        Utils.loadImage(mContext, itemModel.getThumbNails(), holder.getImageView());
        return view;
    }
}
