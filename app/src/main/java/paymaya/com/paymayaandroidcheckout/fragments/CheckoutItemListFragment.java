package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.Bind;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.adapters.CheckoutItemListAdapter;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutItemListFragment extends BaseAbstractFragment {
    private CheckoutItemListFragmentListener mCheckoutItemListFragmentListener;
    private CheckoutItemListAdapter mCheckoutItemListAdapter;

    @Bind(R.id.checkout_fragment_item_list_view)
    ListView mListViewItemList;

    public interface CheckoutItemListFragmentListener {

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.checkout_fragment_item_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCheckoutItemListAdapter = new CheckoutItemListAdapter(getActivity());
        mListViewItemList.setAdapter(mCheckoutItemListAdapter);
    }
}
