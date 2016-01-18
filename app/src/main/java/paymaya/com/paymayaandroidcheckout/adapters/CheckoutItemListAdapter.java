package paymaya.com.paymayaandroidcheckout.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.models.Product;
import paymaya.com.paymayaandroidcheckout.utils.Utils;
import paymaya.com.paymayaandroidcheckout.widgets.CheckoutItemListViewHolder;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutItemListAdapter extends BaseAdapter {
    private CheckoutItemListAdapterListener mCheckoutItemListAdapterListener;
    private List<Product> mProducts;
    private Context mContext;

    public interface CheckoutItemListAdapterListener {
        void onBuyItemClick(int position);
    }

    public CheckoutItemListAdapter(Context context, CheckoutItemListAdapterListener
            checkoutItemListAdapterListener, List<Product> products) {
        mContext = context;
        mCheckoutItemListAdapterListener = checkoutItemListAdapterListener;
        mProducts = products;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mProducts.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        CheckoutItemListViewHolder holder;
        final Product product = mProducts.get(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.checkout_adapter_item_list, parent, false);
            holder = new CheckoutItemListViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CheckoutItemListViewHolder) view.getTag();
        }
        Log.d("@Adapter", "image = " + product.getThumbNails());
        Utils.loadImage(mContext, product.getThumbNails(), holder.getImageViewItem());
        holder.getTextViewItemName().setText(product.getItemName());
        holder.getTextViewItemPrice().setText("PHP " + product.getItemPrice());

        holder.setOnBuyItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckoutItemListAdapterListener.onBuyItemClick(position);
            }
        });
        return view;
    }
}
