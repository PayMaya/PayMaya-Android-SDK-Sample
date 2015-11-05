package paymaya.com.paymayaandroidcheckout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.base.BaseAbstractFragment;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public class UserInformationFragment extends BaseAbstractFragment {
    private UserInformationFragmentListener mUserInformationFragmentListenerCallback;

    @OnClick(R.id.paymaya_checkout_fragment_user_information_button_checkout)
    public void onButtonCheckoutClick() {
        mUserInformationFragmentListenerCallback.onButtonCheckout();
    }

    public interface UserInformationFragmentListener {
        void onButtonCheckout();
    }

    public static UserInformationFragment getInstance() {
        UserInformationFragment userInformationFragment = new UserInformationFragment();
        return userInformationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.paymaya_checkout_fragment_user_information, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mUserInformationFragmentListenerCallback = (UserInformationFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement UserInformationFragmentListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
