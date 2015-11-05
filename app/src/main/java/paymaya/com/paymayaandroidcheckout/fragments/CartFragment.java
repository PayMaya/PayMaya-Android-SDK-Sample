package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.activities.StoreActivity;
import paymaya.com.paymayaandroidcheckout.adapters.ListViewItemAdapter;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;
import paymaya.com.paymayaandroidcheckout.widgets.ListViewItemViewHolder;

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
    RecyclerView mRecyclerView;

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
        ListViewItemAdapter listViewItemAdapter = new ListViewItemAdapter(getActivity(), storeActivity.getItemList());
        mRecyclerView.setAdapter(listViewItemAdapter);
        listViewItemAdapter.notifyDataSetChanged();
    }
}
