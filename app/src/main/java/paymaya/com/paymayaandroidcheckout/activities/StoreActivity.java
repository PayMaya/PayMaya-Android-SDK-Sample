package paymaya.com.paymayaandroidcheckout.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Item;

import java.util.ArrayList;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractActivity;
import paymaya.com.paymayaandroidcheckout.fragments.CartFragment;
import paymaya.com.paymayaandroidcheckout.fragments.ItemListFragment;
import paymaya.com.paymayaandroidcheckout.fragments.StoreFragment;
import paymaya.com.paymayaandroidcheckout.fragments.UserInformationFragment;

/**
 * Created by jadeantolingaa on 11/2/15.
 */
public class StoreActivity extends BaseAbstractActivity implements CartFragment
        .CartFragmentListener, UserInformationFragment.UserInformationFragmentListener,
        ItemListFragment.ItemListFragmentListener {

    private static final int FRAGMENT_CONTAINER = R.id
            .paymaya_checkout_activity_store_fragment_container;

    private List<Item> mItemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymaya_checkout_activity_store);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        replaceFragment(getActivity(), FRAGMENT_CONTAINER, new StoreFragment());
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void onButtonContinue() {
        replaceFragmentAddToBackStack(getActivity(), FRAGMENT_CONTAINER, new
                UserInformationFragment());
    }

    @Override
    public void onButtonCheckout() {
        Toast.makeText(getApplicationContext(), "checkout click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Item item) {
        mItemList.add(item);
    }

    public List<Item> getItemList() {
        return mItemList;
    }
}
