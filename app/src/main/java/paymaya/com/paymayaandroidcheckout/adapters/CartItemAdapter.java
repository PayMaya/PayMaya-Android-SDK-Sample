package paymaya.com.paymayaandroidcheckout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.SampleApplication;
import paymaya.com.paymayaandroidcheckout.models.ItemModel;
import paymaya.com.paymayaandroidcheckout.widgets.CartItemViewHolder;

/**
 * Created by jadeantolingaa on 1/14/16.
 */
public class CartItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<ItemModel> mItemModelList;
    private HashMap<Integer, Integer> mHashMapItemList;

    public CartItemAdapter(Context context, List<ItemModel> itemModels, HashMap<Integer, Integer>
            map) {

        mContext = context;
        mItemModelList = itemModels;
        mHashMapItemList = map;
    }

    @Override
    public int getCount() {
        return mItemModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItemModelList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartItemViewHolder cartItemViewHolder;
        final ItemModel itemModel = mItemModelList.get(position);
        if (null == convertView) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.checkout_fragment_cart_list_item,
                    parent, false);
            cartItemViewHolder = new CartItemViewHolder(convertView);

            convertView.setTag(cartItemViewHolder);
        } else {
            cartItemViewHolder = (CartItemViewHolder) convertView.getTag();
        }

        cartItemViewHolder.getTextViewItemName().setText(itemModel.getItemName());
        cartItemViewHolder.getTextViewItemPrice().setText("" + itemModel.getItemPrice());

        //check if zero or null;
        int quantity = mHashMapItemList.get(0);

        cartItemViewHolder.getTextViewItemQuantity().setText("x " + quantity);
        cartItemViewHolder.getTextViewTotalPrice().setText(itemModel.getItemPrice().intValue() *
                quantity);

        return convertView;
    }
}
