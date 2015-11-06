package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Item;
import com.voyagerinnovation.paymaya_sdk_android_checkout.models.TotalAmount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.activities.StoreActivity;
import paymaya.com.paymayaandroidcheckout.adapters.ListAdapter;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 11/2/15.
 */
public class CartFragment extends BaseAbstractFragment {
    private CartFragmentListener mCartFragmentListenerCallback;

    @OnClick(R.id.paymaya_checkout_fragment_cart_button_continue)
    public void onButtonContinueClicked() {
        mCartFragmentListenerCallback.onButtonContinue();
    }

    @Bind(R.id.paymaya_checkout_fragment_cart_recycler_view)
    ListView mRecyclerView;

    @Bind(R.id.paymaya_checkout_fragment_cart_text_view_total)
    TextView mTextViewTotal;

    private ListAdapter mListAdapter;

    public interface CartFragmentListener {
        void onButtonContinue();
    }

    public static CartFragment getInstance() {
        CartFragment cartFragment = new CartFragment();
        return cartFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.paymaya_checkout_fragment_cart, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCartFragmentListenerCallback = (CartFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement CartFragmentListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StoreActivity storeActivity = (StoreActivity) getActivity();

        mListAdapter = new ListAdapter(getActivity(), storeActivity.getItemList());
        mRecyclerView.setAdapter(mListAdapter);
    }

    public void notifyList(double total) {
        if (mListAdapter != null) {
            mListAdapter.notifyDataSetChanged();
            mTextViewTotal.setText("Total: PHP " + total);
        }
    }
}
