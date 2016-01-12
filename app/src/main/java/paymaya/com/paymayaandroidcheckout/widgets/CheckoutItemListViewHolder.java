package paymaya.com.paymayaandroidcheckout.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import paymaya.com.paymayaandroidcheckout.R;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutItemListViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.checkout_adapter_item_list_image_view)
    ImageView mImageViewItem;

    @Bind(R.id.checkout_adapter_item_list_text_view_item_name)
    TextView mTextViewItemName;

    @Bind(R.id.checkout_adapter_item_list_text_view_item_price)
    TextView mTextViewItemPrice;

    public CheckoutItemListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getImageViewItem() {
        return mImageViewItem;
    }

    public void setImageViewItem(ImageView imageViewItem) {
        mImageViewItem = imageViewItem;
    }

    public TextView getTextViewItemName() {
        return mTextViewItemName;
    }

    public void setTextViewItemName(TextView textViewItemName) {
        mTextViewItemName = textViewItemName;
    }

    public TextView getTextViewItemPrice() {
        return mTextViewItemPrice;
    }

    public void setTextViewItemPrice(TextView textViewItemPrice) {
        mTextViewItemPrice = textViewItemPrice;
    }
}
