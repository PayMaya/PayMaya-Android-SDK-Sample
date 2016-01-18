package paymaya.com.paymayaandroidcheckout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.models.Product;
import paymaya.com.paymayaandroidcheckout.widgets.CartItemViewHolder;

/**
 * Created by jadeantolingaa on 1/14/16.
 */
public class CartItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<Product> mProductList;
    private HashMap<Integer, Integer> mHashMapItemList;

    public CartItemAdapter(Context context, List<Product> products, HashMap<Integer, Integer>
            map) {

        mContext = context;
        mProductList = products;
        mHashMapItemList = map;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartItemViewHolder cartItemViewHolder;
        final Product product = mProductList.get(position);
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

        if (mHashMapItemList.size() != 0) {
            if (mHashMapItemList.get(position) != null) {
                if (mHashMapItemList.get(position) != 0) {

                    int quantity = mHashMapItemList.get(position);
                    cartItemViewHolder.getTextViewItemName().setText(product.getItemName());
                    cartItemViewHolder.getTextViewItemPrice().setText("" + product.getItemPrice());

                    cartItemViewHolder.getTextViewItemQuantity().setText("x " + quantity);
                    BigDecimal total = product.getItemPrice().multiply(BigDecimal.valueOf(quantity));
                    cartItemViewHolder.getTextViewTotalPrice().setText(total + "");
                }
            }
        }

        return convertView;
    }
}
