package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;
import com.paymaya.sdk.android.checkout.models.Item;

import butterknife.Bind;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.adapters.CardItemAdapter;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 11/2/15.
 */
public class ItemListFragment extends BaseAbstractFragment {
    private ItemListFragmentListener mItemListFragmentListenerCallback;

    @Bind(R.id.paymaya_checkout_fragment_item_list_grid_view)
    StaggeredGridView mGridView;

    public interface ItemListFragmentListener {
        void onItemClick(Item item);
    }

    public static ItemListFragment getInstance() {
        ItemListFragment itemListFragment = new ItemListFragment();
        return itemListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.paymaya_checkout_fragment_item_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mItemListFragmentListenerCallback = (ItemListFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " +
                    "ItemListFragmentListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridView.setAdapter(new CardItemAdapter(getActivity()));

//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Product itemModel = ((Product) mGridView.getAdapter().getItem(position));
//                mItemListFragmentListenerCallback.onItemClick(itemModel.getItem());
//                Toast.makeText(getActivity(), itemModel.getItem().getName(), Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
    }

    @Override
    public void initialize() {

    }
}
