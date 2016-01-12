package paymaya.com.paymayaandroidcheckout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.paymaya.sdk.android.checkout.PayMayaCheckout;
import com.paymaya.sdk.android.checkout.PayMayaCheckoutCallback;
import com.paymaya.sdk.android.checkout.models.Item;

import java.util.ArrayList;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractActivity;
import paymaya.com.paymayaandroidcheckout.fragments.CheckoutItemListFragment;
import paymaya.com.paymayaandroidcheckout.fragments.StoreFragment;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutActivity extends BaseAbstractActivity implements PayMayaCheckoutCallback,
        CheckoutItemListFragment.CheckoutItemListFragmentListener {
    private static final int FRAGMENT_CONTAINER = R.id
            .paymaya_checkout_activity_store_fragment_container;
    private static final long PRODUCT_ID = 6319921;

    private static final String SUCCESS_URL = "http://shop.someserver.com/success?id=" + PRODUCT_ID;
    private static final String FAILURE_URL = "http://shop.someserver.com/failure?id=" + PRODUCT_ID;
    private static final String CANCEL_URL = "http://shop.someserver.com/cancel?id=" + PRODUCT_ID;
    private static final String CHECKOUT_REQUEST_REFERENCE_NUMBER = "000141386713";
    private static final String CHECKOUT_CURRENCY = "PHP";
    private static final String CLIENT_KEY = "pk-iaioBC2pbY6d3BVRSebsJxghSHeJDW4n6navI7tYdrN";

    private List<Item> mItemList = new ArrayList<>();
    private PayMayaCheckout payMayaCheckout;


    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymaya_checkout_activity_store);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        replaceFragment(getActivity(), FRAGMENT_CONTAINER, new CheckoutItemListFragment());

        /**
         * Initialize PayMayaCheckout instance variable at onCreate
         * Implements PayMayaCheckoutCallback in the class to be passed at the constructor
         * Create Client Key string to be passed at the constructor
         * Passed Client Key and PayMayaCheckoutCallback in PayMayaCheckout constructor
         *
         * params - (String) client_key, (Class implemented with PayMayaCheckoutCallback)
         */

        payMayaCheckout = new PayMayaCheckout(CLIENT_KEY, this);
    }

    public List<Item> getItemList() {
        return mItemList;
    }

    public void setItemList(List<Item> itemList) {
        mItemList = itemList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * Call PayMayaCheckout onActivityResult(requestCode, resultCode, data)
         * to have access in the implemented methods of PayMayaCheckoutCallback
         * which is the onCheckoutSuccess(), onCheckoutCanceled(), onCheckoutFailure()
         *
         * @params - requestCode, resultCode, data
         */
        payMayaCheckout.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCheckoutSuccess() {
        Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckoutCanceled() {
        Toast.makeText(this, "Result Canceled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckoutFailure() {
        Toast.makeText(this, "Result Failure", Toast.LENGTH_SHORT).show();
    }
}
