package paymaya.com.paymayaandroidcheckout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.paymaya.checkoutsdkandroid.PayMayaCheckout;
import com.paymaya.checkoutsdkandroid.PayMayaCheckoutCallback;
import com.paymaya.checkoutsdkandroid.models.Buyer;
import com.paymaya.checkoutsdkandroid.models.Checkout;
import com.paymaya.checkoutsdkandroid.models.Item;
import com.paymaya.checkoutsdkandroid.models.RedirectUrl;
import com.paymaya.checkoutsdkandroid.models.TotalAmount;

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
        ItemListFragment.ItemListFragmentListener, PayMayaCheckoutCallback {
    private static final String TAG = StoreActivity.class.getSimpleName();

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

    private PayMayaCheckout payMayaCheckout;

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.valueOf(0.00);
        for (Item item : mItemList) {
            total = total.add(item.getTotalAmount().getValue());
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
        /**
         * Create RedirectUrl object to be needed in creating Checkout object
         * @params - (String) success_url, (String) failure_url, (String) cancel_url
         */
        RedirectUrl redirectUrl = new RedirectUrl(SUCCESS_URL, FAILURE_URL, CANCEL_URL);

        /**
         * Create TotalAmount object to be needed in creating Checkout object
         * @params - (BigDecimal) total amount of item, (String) currency
         */
        TotalAmount totalAmount = new TotalAmount(getTotal(), CHECKOUT_CURRENCY);

        /**
         * Create Checkout object to be passed in executing checkout sdk
         * @params - (TotalAmount) totalAmount, (Buyer) buyer, (List<Item>) item,
         *          (String) reference_number, (RedirectUrl) redirect_url
         */
        Checkout checkout = new Checkout(totalAmount, buyer, mItemList,
                CHECKOUT_REQUEST_REFERENCE_NUMBER, redirectUrl);

        /**
         * Call PayMayaCheckout execute method
         * @params - (Activity) this, (Checkout) checkout
         */
        payMayaCheckout.execute(this, checkout);

        Toast.makeText(getApplicationContext(), "Checkout button click", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(StoreActivity.this, "Result OK", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckoutCanceled() {
        Toast.makeText(StoreActivity.this, "Result Canceled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckoutFailure() {
        Toast.makeText(StoreActivity.this, "Result Failure", Toast.LENGTH_SHORT).show();
    }
}
