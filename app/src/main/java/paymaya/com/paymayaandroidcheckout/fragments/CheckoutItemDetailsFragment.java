package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutItemDetailsFragment extends BaseAbstractFragment {
    private CheckoutItemDetailsFragmentListener mCheckoutItemDetailsFragmentListener;

    public interface CheckoutItemDetailsFragmentListener {
        void onButtonAddToCartClick();
    }

    public static CheckoutItemDetailsFragment getInstance() {
        CheckoutItemDetailsFragment checkoutItemDetailsFragment = new CheckoutItemDetailsFragment();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
