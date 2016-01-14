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
import paymaya.com.paymayaandroidcheckout.activities.StoreActivity;
import paymaya.com.paymayaandroidcheckout.adapters.ViewPagerAdapter;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 11/2/15.
 */
public class StoreFragment extends BaseAbstractFragment {
    private ViewPager mViewPager;

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
        setViewPager();
    }

    @Override
    public void initialize() {

    }

    private void setViewPager() {
        Log.d("@StoreFragment", "setViewPager");
        mViewPager = (ViewPager) getActivity().findViewById(R.id
                .paymaya_checkout_fragment_store_viewpager);
        if (null != mViewPager) {
            setUpViewPager(mViewPager);
        }
    }

    private void setUpViewPager(ViewPager viewPager) {
        Log.d("@StoreFragment", "setUpViewPager");
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ItemListFragment(), "Items");
        adapter.addFragment(new CartFragment(), "Cart");
        viewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
                CartFragment frag = (CartFragment) adapter.instantiateItem(mViewPager, 1);
                StoreActivity storeActivity = (StoreActivity) getActivity();
                frag.notifyList(storeActivity.getTotal());
                Log.d("@onPageScrolled", " Viewpager scrolling");
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id
                .paymaya_checkout_fragment_store_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

}
