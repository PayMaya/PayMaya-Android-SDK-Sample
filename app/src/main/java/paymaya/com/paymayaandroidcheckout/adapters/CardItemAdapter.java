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
import paymaya.com.paymayaandroidcheckout.widgets.CardItemViewHolder;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class CardItemAdapter extends BaseAdapter {

    private List<Product> mProducts;
    private Context mContext;

    public CardItemAdapter(Context context) {
        super();
        mContext = context;
        //mProducts = Utils.getItemModels();
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
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        CardItemViewHolder holder;
        Product product = mProducts.get(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.paymaya_checkout_image_list_item, parent, false);
            holder = new CardItemViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CardItemViewHolder) view.getTag();
        }
        Log.d("@Adapter", "image = " + product.getThumbNails());
        Utils.loadImage(mContext, product.getThumbNails(), holder.getImageView());
        return view;
    }
}
