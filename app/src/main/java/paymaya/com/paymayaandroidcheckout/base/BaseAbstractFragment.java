package paymaya.com.paymayaandroidcheckout.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by jadeantolingaa on 11/3/15.
 */
public abstract class BaseAbstractFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initialize();
    }

    public abstract void initialize();
}
