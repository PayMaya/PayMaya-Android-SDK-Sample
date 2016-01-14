package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutItemCartFragment extends BaseAbstractFragment {
    private CheckoutItemCartFragmentListener mCheckoutItemCartFragmentListener;

    @Bind(R.id.checkout_fragment_cart_text_view_sub_total)
    TextView mTextViewSubTotal;

    @Bind(R.id.checkout_fragment_cart_text_view_shipping_fee)
    TextView mTextViewShippingFee;

    @Bind(R.id.checkout_fragment_cart_text_view_tax)
    TextView mTextViewTax;

    @Bind(R.id.checkout_fragment_cart_text_view_total)
    TextView mTextViewTotal;

    @Bind(R.id.checkout_fragment_cart_list_view)
    ListView mListViewItemCart;

    @OnClick(R.id.checkout_fragment_cart_button_continue)
    public void onButtonContinueClick() {
        mCheckoutItemCartFragmentListener.onButtonContinueClick();
    }

    public interface CheckoutItemCartFragmentListener {
        void onButtonContinueClick();
    }

    public static CheckoutItemCartFragment getInstance() {
        CheckoutItemCartFragment checkoutItemCartFragment = new CheckoutItemCartFragment();
        return checkoutItemCartFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCheckoutItemCartFragmentListener = (CheckoutItemCartFragmentListener) context;
        } catch (ClassCastException ce) {
            throw new ClassCastException(context.toString() + "must implement " +
                    "CheckoutItemCartFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        return inflater.inflate(R.layout.checkout_fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initialize() {

    }
}
