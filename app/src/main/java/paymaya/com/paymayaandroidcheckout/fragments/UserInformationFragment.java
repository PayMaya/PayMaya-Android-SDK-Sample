package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.paymaya.sdk.android.checkout.models.Address;
import com.paymaya.sdk.android.checkout.models.Buyer;
import com.paymaya.sdk.android.checkout.models.Contact;

import butterknife.Bind;
import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class UserInformationFragment extends BaseAbstractFragment {
    private UserInformationFragmentListener mUserInformationFragmentListenerCallback;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_first_name)
    EditText mEditTextFirstName;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_middle_name)
    EditText mEditTextMiddleName;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_last_name)
    EditText mEditTextLastName;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_mobile_number)
    EditText mEditTextMobileNumber;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_email)
    EditText mEditTextEmail;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_line1)
    EditText mEditTextLine1;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_line2)
    EditText mEditTextLine2;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_city)
    EditText mEditTextCity;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_state)
    EditText mEditTextState;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_zip_code)
    EditText mEditTextZipCode;

    @Bind(R.id.paymaya_checkout_fragment_user_information_edit_text_country_code)
    EditText mEditTextCountryCode;

    @OnClick(R.id.paymaya_checkout_fragment_user_information_button_checkout)
    public void onButtonCheckoutClick() {
        String first_name = mEditTextFirstName.getText().toString().trim();
        String last_name = mEditTextLastName.getText().toString().trim();
        String middle_name = mEditTextMiddleName.getText().toString().trim();

        String mobile_number = mEditTextMobileNumber.getText().toString().trim();
        String email = mEditTextEmail.getText().toString().trim();

        String line1 = mEditTextLine1.getText().toString().trim();
        String line2 = mEditTextLine2.getText().toString().trim();
        String city = mEditTextCity.getText().toString().trim();
        String state = mEditTextState.getText().toString().trim();
        String zip_code = mEditTextZipCode.getText().toString().trim();
        String country_code = mEditTextCountryCode.getText().toString().trim();

        /**
         * Create Contact for Buyer object
         *
         * @params - (String) mobile_number, (String) email
         */
        Contact contact = new Contact(mobile_number, email);

        /**
         * Create Address for Billing and Shipping Address to be needed in Buyer object
         *
         * @params - (String) line1, (String) line2, (String) city, (String) state,
         *           (String) zip_code, (String) country_code
         */
        Address address = new Address(line1, line2, city, state, zip_code, country_code);

        /**
         * Create Buyer for Checkout object
         *
         * @params - (String) first_name, (String) middle_name, (String) last_name
         * @setmethods - Contact,
         *               BillingAddress,
         *               ShippingAddress
         */
        Buyer buyer = new Buyer(first_name, middle_name, last_name);
        buyer.setContact(contact);
        buyer.setBillingAddress(address);
        buyer.setShippingAddress(address);

        /**
         * Passed Buyer instance variable for Checkout object
         *
         */
        mUserInformationFragmentListenerCallback.onButtonCheckout(buyer);
    }

    public interface UserInformationFragmentListener {

        void onButtonCheckout(Buyer buyer);
    }

    public static UserInformationFragment getInstance() {
        UserInformationFragment userInformationFragment = new UserInformationFragment();
        return userInformationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.paymaya_checkout_fragment_user_information, container,
                false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mUserInformationFragmentListenerCallback = (UserInformationFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " +
                    "UserInformationFragmentListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
