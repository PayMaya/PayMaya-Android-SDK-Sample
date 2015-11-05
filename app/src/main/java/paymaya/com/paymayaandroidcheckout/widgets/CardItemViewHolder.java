package paymaya.com.paymayaandroidcheckout.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import paymaya.com.paymayaandroidcheckout.R;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class CardItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.paymaya_checkout_image_list_item_image_view)
    ImageView mImageView;

    public CardItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public void setImageView(ImageView imageView) {
        mImageView = imageView;
    }
}
