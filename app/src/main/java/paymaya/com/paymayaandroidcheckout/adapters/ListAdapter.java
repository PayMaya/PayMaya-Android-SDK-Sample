package paymaya.com.paymayaandroidcheckout.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Item;

import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.widgets.ListViewItemViewHolder;

/**
 * Created by jadeantolingaa on 11/6/15.
 */
public class ListAdapter extends BaseAdapter {

    private List<Item> mItemModelList;
    private Context mContext;

    public ListAdapter(Context context, List<Item> itemModelList) {
        mItemModelList = itemModelList;
        mContext = context;
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
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ListViewItemViewHolder holder;
        Item item = mItemModelList.get(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.paymaya_checkout_list_view_item, parent, false);
            holder = new ListViewItemViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ListViewItemViewHolder) view.getTag();
        }
        Log.d("@ListAdapter", "name = " + item.getName());
        holder.getTextViewName().setText(item.getName());
        holder.getTextViewPrice().setText("Quantity :" + item.getQuantity()
                 + " Total Amount: " + item.getTotalAmount().getValue().toString());
        return view;
    }
}
