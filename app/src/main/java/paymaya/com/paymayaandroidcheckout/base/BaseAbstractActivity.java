package paymaya.com.paymayaandroidcheckout.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jadeantolingaa on 11/2/15.
 */
public abstract class BaseAbstractActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    public abstract Activity getActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setButterKnife();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setButterKnife();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setButterKnife();
    }

    private void setButterKnife() {
        ButterKnife.bind(getActivity());
    }

    public static  void replaceFragment(AppCompatActivity activity, int resId, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(resId, fragment, createTag(activity, fragment))
                .commit();
    }

    public static  void replaceFragmentAddToBackStack(AppCompatActivity activity, int resId, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(resId, fragment, createTag(activity, fragment))
                .addToBackStack(createTag(activity, fragment))
                .commit();
    }

    public static void removeFragment(AppCompatActivity activity, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commit();
    }

    public static void addFragment(AppCompatActivity activity, int resId, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .add(resId, fragment, createTag(activity, fragment))
                .commit();
    }

    public static String createTag(AppCompatActivity activity, Fragment fragment) {
        return activity.getClass().getName() + ":" + fragment.getClass().getName();
    }
}
