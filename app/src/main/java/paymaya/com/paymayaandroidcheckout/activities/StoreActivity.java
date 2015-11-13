package paymaya.com.paymayaandroidcheckout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.voyagerinnovation.paymaya_sdk_android_checkout.PayMayaCheckout;
import com.voyagerinnovation.paymaya_sdk_android_checkout.PayMayaCheckoutCallback;
import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Buyer;
import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Checkout;
import com.voyagerinnovation.paymaya_sdk_android_checkout.models.Item;
import com.voyagerinnovation.paymaya_sdk_android_checkout.models.RedirectUrl;
import com.voyagerinnovation.paymaya_sdk_android_checkout.models.TotalAmount;

import java.math.BigDecimal;
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

    private static final String CLIENT_KEY = "8510f691-8c0b-4f28-bfa0-bcced0cb0fd2";
    private static final String CLIENT_SECRET = "";

    private static final String CHECKOUT_REQUEST_REFERENCE_NUMBER = "000141386713";
    private static final String CHECKOUT_CURRENCY = "PHP";

    private static final long PRODUCT_ID = 6319921;
    private static final String SUCCESS_URL = "http://shop.someserver.com/success?id=" + PRODUCT_ID;
    private static final String FAILURE_URL = "http://shop.someserver.com/failure?id=" + PRODUCT_ID;
    private static final String CANCEL_URL = "http://shop.someserver.com/cancel?id=" + PRODUCT_ID;

    private List<Item> mItemList = new ArrayList<>();

    private PayMayaCheckout mPayMayaCheckout;

    public double getTotal() {
        double total = 0.0;
        for (Item item : mItemList) {
            total = total + item.getTotalAmount().getValue().doubleValue();
        }
        return total;
    }

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
    public void onButtonCheckout(Buyer buyer) {
        RedirectUrl redirectUrl = new RedirectUrl(SUCCESS_URL, FAILURE_URL, CANCEL_URL);

        TotalAmount totalAmount = new TotalAmount(BigDecimal.valueOf(getTotal()), CHECKOUT_CURRENCY);

        Checkout checkout = new Checkout(totalAmount, buyer, mItemList, CHECKOUT_REQUEST_REFERENCE_NUMBER, redirectUrl);

        mPayMayaCheckout = new PayMayaCheckout(CLIENT_KEY, CLIENT_SECRET, new PayMayaCheckoutCallback() {
            @Override
            public void onCheckoutSuccess() {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCheckoutCanceled() {
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCheckoutFailure() {
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
        mPayMayaCheckout.execute(this, checkout);
    }

    @Override
    public void onItemClick(Item item) {
        mItemList.add(item);
    }

    public List<Item> getItemList() {
        return mItemList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPayMayaCheckout.onActivityResult(requestCode, resultCode, data);
    }
}
