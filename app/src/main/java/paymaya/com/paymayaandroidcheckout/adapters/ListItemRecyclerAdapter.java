package paymaya.com.paymayaandroidcheckout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Item;
import com.voyagerinnovation.paymaya_sdk_android_checkout.models.ItemAmount;

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
public class ListItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemModel> mItemModels;
    private Context mContext;

    public ListItemRecyclerAdapter(Context context) {
        super();
        mContext = context;
        mItemModels = new ArrayList<ItemModel>();

        ItemModel itemModel = new ItemModel();
        Item item = itemModel.getItem();
        ItemAmount itemAmount = item.getItemAmount();

        //itemModel.setThumbNails(R.mipmap.bag);
        item.setName("Bag One");
        item.setSkuCode("CVG-096732");
        item.setDescription("It's a Bag");
        item.setQuantity(new BigDecimal(1));
        itemAmount.setValue(new BigDecimal(383.80));
        mItemModels.add(itemModel);

        itemModel = new ItemModel();
        item = itemModel.getItem();
        itemAmount = item.getItemAmount();

       // itemModel.setThumbNails(R.mipmap.shoe);
        item.setName("Shoe One");
        item.setSkuCode("CVG-096733");
        item.setDescription("It's a Shoe");
        item.setQuantity(new BigDecimal(1));
        itemAmount.setValue(new BigDecimal(383.80));
        mItemModels.add(itemModel);

        itemModel = new ItemModel();
        item = itemModel.getItem();
        itemAmount = item.getItemAmount();

       // itemModel.setThumbNails(R.mipmap.necklace);
        item.setName("Necklae One");
        item.setSkuCode("CVG-096734");
        item.setDescription("It's a Necklase");
        item.setQuantity(new BigDecimal(1));
        itemAmount.setValue(new BigDecimal(383.80));
        mItemModels.add(itemModel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paymaya_checkout_fragment_item_list, parent, false);

        return new CardItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardItemViewHolder cardItemViewHolder = (CardItemViewHolder) holder;

        ItemModel itemModel = mItemModels.get(position);
        Utils.loadImage(mContext, itemModel.getThumbNails(), cardItemViewHolder.getImageView());
    }

    @Override
    public int getItemCount() {
        return mItemModels.size();
    }
}
