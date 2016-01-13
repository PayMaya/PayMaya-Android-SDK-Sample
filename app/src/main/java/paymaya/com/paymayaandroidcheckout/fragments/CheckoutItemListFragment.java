package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.paymaya.sdk.android.checkout.models.Buyer;
import com.paymaya.sdk.android.checkout.models.Item;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.SampleApplication;
import paymaya.com.paymayaandroidcheckout.activities.CheckoutActivity;
import paymaya.com.paymayaandroidcheckout.adapters.CheckoutItemListAdapter;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;
import paymaya.com.paymayaandroidcheckout.models.ItemModel;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutItemListFragment extends BaseAbstractFragment implements
        CheckoutItemListAdapter.CheckoutItemListAdapterListener {

    private CheckoutItemListFragmentListener mCheckoutItemListFragmentListener;
    private CheckoutItemListAdapter mCheckoutItemListAdapter;

    @Bind(R.id.checkout_fragment_item_list_view)
    ListView mListViewItemList;

    public interface CheckoutItemListFragmentListener {
        void onButtonBuy(int position);
    }

    public static CheckoutItemListFragment getInstance() {
        CheckoutItemListFragment checkoutItemListFragment = new CheckoutItemListFragment();
        return checkoutItemListFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCheckoutItemListFragmentListener = (CheckoutItemListFragmentListener) context;
        } catch (ClassCastException ce) {
            throw new ClassCastException(context.toString() + " must implement " +
                    "CheckoutItemListFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        return inflater.inflate(R.layout.checkout_fragment_item_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCheckoutItemListAdapter = new CheckoutItemListAdapter(getActivity(), this, (
                (SampleApplication) getActivity().getApplication()).getItemModelList());
        mListViewItemList.setAdapter(mCheckoutItemListAdapter);

        HashMap<Integer, Integer> map = ((SampleApplication) getActivity().getApplication()).getHashMap();

        Toast.makeText(getContext(), "Position 0 : " + map.get(0) +
                                    "Position 1 : " + map.get(1) +
                                    "Position 2 : " + map.get(2), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onBuyItemClick(int position) {
        mCheckoutItemListFragmentListener.onButtonBuy(position);
    }
}