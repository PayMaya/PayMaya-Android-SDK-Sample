package paymaya.com.paymayaandroidcheckout.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.adapters.ViewPagerAdapter;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 11/2/15.
 */
public class StoreFragment extends BaseAbstractFragment {

    private ItemListFragment mItemListFragment;
    private CartFragment mCartFragment;

    public static StoreFragment getInstance() {
        StoreFragment storeFragment = new StoreFragment();
        return storeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.paymaya_checkout_fragment_store, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mItemListFragment = new ItemListFragment();
        mCartFragment = new CartFragment();
        setViewPager();
    }

    private void setViewPager() {
        Log.d("@StoreFragment", "setViewPager");
        ViewPager mViewPager = (ViewPager) getActivity().findViewById(R.id
                .paymaya_checkout_fragment_store_viewpager);
        if (null != mViewPager) {
            setUpViewPager(mViewPager);
        }
    }

    private void setUpViewPager(ViewPager viewPager) {
        Log.d("@StoreFragment", "setUpViewPager");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ItemListFragment(), "Items");
        adapter.addFragment(new CartFragment(), "Cart");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id
                .paymaya_checkout_fragment_store_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

}
