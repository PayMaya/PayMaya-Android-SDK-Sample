package paymaya.com.paymayaandroidcheckout.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.paymaya.sdk.android.checkout.PayMayaCheckout;
import com.paymaya.sdk.android.checkout.PayMayaCheckoutCallback;
import com.paymaya.sdk.android.checkout.models.Buyer;
import com.paymaya.sdk.android.checkout.models.Checkout;
import com.paymaya.sdk.android.checkout.models.Item;
import com.paymaya.sdk.android.checkout.models.RedirectUrl;
import com.paymaya.sdk.android.checkout.models.TotalAmount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.SampleApplication;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractActivity;
import paymaya.com.paymayaandroidcheckout.fragments.CheckoutItemCartFragment;
import paymaya.com.paymayaandroidcheckout.fragments.CheckoutItemDetailsFragment;
import paymaya.com.paymayaandroidcheckout.fragments.CheckoutItemListFragment;
import paymaya.com.paymayaandroidcheckout.fragments.UserInformationFragment;
import paymaya.com.paymayaandroidcheckout.models.Product;
import paymaya.com.paymayaandroidcheckout.utils.ListItem;

/**
 * Created by jadeantolingaa on 1/12/16.
 */
public class CheckoutActivity extends BaseAbstractActivity implements PayMayaCheckoutCallback,
        CheckoutItemListFragment.CheckoutItemListFragmentListener, CheckoutItemDetailsFragment
                .CheckoutItemDetailsFragmentListener, CheckoutItemCartFragment
                .CheckoutItemCartFragmentListener, UserInformationFragment
                .UserInformationFragmentListener {
    private static final int FRAGMENT_CONTAINER = R.id
            .paymaya_checkout_activity_store_fragment_container;
    private static final long PRODUCT_ID = 6319921;

    private static final String SUCCESS_URL = "http://shop.someserver.com/success?id=" + PRODUCT_ID;
    private static final String FAILURE_URL = "http://shop.someserver.com/failure?id=" + PRODUCT_ID;
    private static final String CANCEL_URL = "http://shop.someserver.com/cancel?id=" + PRODUCT_ID;
    private static final String CHECKOUT_REQUEST_REFERENCE_NUMBER = "000141386713";
    private static final String CHECKOUT_CURRENCY = "PHP";
    private static final String CLIENT_KEY = "pk-iaioBC2pbY6d3BVRSebsJxghSHeJDW4n6navI7tYdrN";

    private PayMayaCheckout payMayaCheckout;
    private List<Product> mProductList;
    private HashMap<Integer, Integer> mHashMapItem;

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

        mHashMapItem = ((SampleApplication) getActivity().getApplication()).getHashMap();
        mProductList = ((SampleApplication) getActivity().getApplication()).getProductList();

        /**
         * Initialize PayMayaCheckout instance variable at onCreate
         * Implements PayMayaCheckoutCallback in the class to be passed at the constructor
         * Create Client Key string to be passed at the constructor
         * Passed Client Key and PayMayaCheckoutCallback in PayMayaCheckout constructor
         *
         * params - (String) client_key, (Class implemented with PayMayaCheckoutCallback)
         */

        payMayaCheckout = new PayMayaCheckout(CLIENT_KEY, this);
        replaceFragment(getActivity(), FRAGMENT_CONTAINER, new CheckoutItemListFragment());
    }

    @Override
    public void onButtonBuy(int position) {
        replaceFragmentAddToBackStack(getActivity(), FRAGMENT_CONTAINER,
                CheckoutItemDetailsFragment.getInstance(position));
    }

    @Override
    public void onButtonAddToCartClick() {
        onBackPressed();
    }

    @Override
    public void onButtonContinueClick() {
        replaceFragmentAddToBackStack(getActivity(), FRAGMENT_CONTAINER,
                new UserInformationFragment());
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
        Checkout checkout = new Checkout(totalAmount, buyer, getItems(),
                CHECKOUT_REQUEST_REFERENCE_NUMBER, redirectUrl);

        /**
         * Call PayMayaCheckout execute method
         * @params - (Activity) this, (Checkout) checkout
         */
        payMayaCheckout.execute(this, checkout);

    }

    private BigDecimal getTotal() {
        BigDecimal total = new BigDecimal("0");

        if (!mHashMapItem.isEmpty()) {
            BigDecimal subTotal = new BigDecimal("0");
            for (int i = 0; i <= mHashMapItem.size(); i++) {
                if (mHashMapItem.get(i) != null) {
                    if (mHashMapItem.get(i) != 0) {
                        int quantity = mHashMapItem.get(i);
                        subTotal = mProductList.get(i).getItemPrice().multiply(BigDecimal.valueOf
                                (quantity));
                    }
                }
            }
            BigDecimal shippingFee = subTotal.multiply(BigDecimal.valueOf(0.05));
            BigDecimal tax = subTotal.multiply(BigDecimal.valueOf(0.12));

            BigDecimal extra = shippingFee.add(tax);
            total = subTotal.add(extra);
        }

        return total.setScale(2, BigDecimal.ROUND_UNNECESSARY);
    }

    private List<Item> getItems() {
        List<Item> mItems = new ArrayList<>();
        Item item;

        for (int i = 0; i <= mProductList.size(); i++) {
            if (!mHashMapItem.isEmpty()) {
                if (mHashMapItem.get(i) != null) {
                    if (mHashMapItem.get(i) != 0) {
                        int quantity = mHashMapItem.get(i);
                        TotalAmount amount = new TotalAmount(mProductList.get(i).getItemPrice()
                                .multiply(BigDecimal.valueOf(quantity)), "PHP");
                        item = new Item(mProductList.get(i).getItemName(), quantity, amount);
                        mItems.add(item);
                    }
                }
            }
        }

        return mItems;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.checkout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkout_menu_view_cart:
                replaceFragmentAddToBackStack(getActivity(), FRAGMENT_CONTAINER,
                        new CheckoutItemCartFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}