package paymaya.com.paymayaandroidcheckout.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import paymaya.com.paymayaandroidcheckout.R;

/**
 * Created by jadeantolingaa on 1/14/16.
 */
public class CartItemViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.checkout_fragment_cart_list_item_text_view_item_name)
    TextView mTextViewItemName;

    @Bind(R.id.checkout_fragment_cart_list_item_text_view_item_price)
    TextView mTextViewItemPrice;

    @Bind(R.id.checkout_fragment_cart_list_item_text_view_item_quantity)
    TextView mTextViewItemQuantity;

    @Bind(R.id.checkout_fragment_cart_list_item_text_view_item_total_price)
    TextView mTextViewTotalPrice;

    public CartItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
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

    public TextView getTextViewItemQuantity() {
        return mTextViewItemQuantity;
    }

    public void setTextViewItemQuantity(TextView textViewItemQuantity) {
        mTextViewItemQuantity = textViewItemQuantity;
    }

    public TextView getTextViewTotalPrice() {
        return mTextViewTotalPrice;
    }

    public void setTextViewTotalPrice(TextView textViewTotalPrice) {
        mTextViewTotalPrice = textViewTotalPrice;
    }
}
