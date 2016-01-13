package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.SampleApplication;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;
import paymaya.com.paymayaandroidcheckout.models.ItemModel;
import paymaya.com.paymayaandroidcheckout.utils.Utils;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutItemDetailsFragment extends BaseAbstractFragment {
    private static final String BUNDLE_TAG_ITEM_POSITION = "item_position";

    private CheckoutItemDetailsFragmentListener mCheckoutItemDetailsFragmentListener;

    private int itemQuantity = 0;
    private int pos;

    private HashMap<Integer, Integer> mHashMap;

    @Bind(R.id.checkout_fragment_item_details_image_view)
    ImageView mImageView;

    @Bind(R.id.checkout_fragment_item_details_text_view_item_name)
    TextView mTextViewItemName;

    @Bind(R.id.checkout_fragment_item_details_text_view_item_description)
    TextView mTextViewItemDescription;

    @Bind(R.id.checkout_fragment_item_details_text_view_item_price)
    TextView mTextViewItemPrice;

    @Bind(R.id.checkout_fragment_item_details_text_view_item_quantity)
    TextView mTextViewItemQuantity;

    @OnClick(R.id.checkout_fragment_item_details_button_plus)
    public void onButtonPlusClick(View view) {
        itemQuantity++;
        mTextViewItemQuantity.setText(itemQuantity + "");
    }

    @OnClick(R.id.checkout_fragment_item_details_button_minus)
    public void onButtonMinusClick(View view) {
        itemQuantity--;
        mTextViewItemQuantity.setText(itemQuantity + "");
    }

    @OnClick(R.id.checkout_fragment_item_details_button_add_to_cart)
    public void onButtonAddToCartClick(View view) {
        //get hashmap quantity then add the itemQuantity
        mHashMap.put(pos, itemQuantity);
        ((SampleApplication) getActivity().getApplication()).setHashMap(mHashMap);
        getActivity().onBackPressed();
    }

    public interface CheckoutItemDetailsFragmentListener {
        void onButtonAddToCartClick();
    }

    public static CheckoutItemDetailsFragment getInstance(int position) {
        CheckoutItemDetailsFragment checkoutItemDetailsFragment = new CheckoutItemDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_TAG_ITEM_POSITION, position);
        checkoutItemDetailsFragment.setArguments(bundle);
        return checkoutItemDetailsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCheckoutItemDetailsFragmentListener = (CheckoutItemDetailsFragmentListener) context;
        } catch (ClassCastException ce) {
            throw new ClassCastException(context.toString() + " must implement " +
                    "CheckoutItemDetailsFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        return inflater.inflate(R.layout.checkout_fragment_item_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initialize() {
        Bundle bundle = getArguments();
        if(null != bundle){
            pos = bundle.getInt(BUNDLE_TAG_ITEM_POSITION);
            ItemModel itemModel = ((SampleApplication) getActivity().getApplication()).getItemModelList().get(pos);
            mTextViewItemName.setText(itemModel.getItemName());
            mTextViewItemDescription.setText(itemModel.getItemDescription());
            mTextViewItemPrice.setText("PHP " + itemModel.getItemPrice());
            Utils.loadImage(getActivity(), itemModel.getThumbNails(), mImageView);
        }

        mHashMap = ((SampleApplication) getActivity().getApplication()).getHashMap();
    }
}
