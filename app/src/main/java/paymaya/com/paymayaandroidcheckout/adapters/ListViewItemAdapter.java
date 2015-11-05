package paymaya.com.paymayaandroidcheckout.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Item;

import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.widgets.ListViewItemViewHolder;

/**
 * Created by jadeantolingaa on 11/4/15.
 */
public class ListViewItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> mItemModelList;
    private Context mContext;

    public ListViewItemAdapter(Context context, List<Item> itemModelList) {
        mItemModelList = itemModelList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.paymaya_checkout_list_view_item, parent, false);

        return new ListViewItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListViewItemViewHolder listViewItemViewHolder = (ListViewItemViewHolder) holder;

        Item itemModel = mItemModelList.get(position);
        listViewItemViewHolder.getTextViewName().setText(itemModel.getName());
        listViewItemViewHolder.getTextViewPrice().setText(itemModel.getTotalAmount().getValue().toString());
    }

    @Override
    public int getItemCount() {
        return mItemModelList.size();
    }
}
