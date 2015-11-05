package paymaya.com.paymayaandroidcheckout.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import paymaya.com.paymayaandroidcheckout.R;

/**
 * Created by jadeantolingaa on 11/4/15.
 */
public class ListViewItemViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.paymaya_checkout_list_view_item_text_view_name)
    TextView mTextViewName;

    @Bind(R.id.paymaya_checkout_list_view_item_text_view_price)
    TextView mTextViewPrice;

    public ListViewItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public TextView getTextViewName() {
        return mTextViewName;
    }

    public TextView getTextViewPrice() {
        return mTextViewPrice;
    }
}
