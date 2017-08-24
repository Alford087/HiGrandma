package com.mars.higrandma.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * Created by tc on 2016/1/26.
 */
public abstract class BaseFragment extends Fragment {

    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getFragmentLayoutRes() > 0) {
            return inflater.inflate(getFragmentLayoutRes(), null);
        } else {
            return new LinearLayout(getContext());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        initComponent(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public abstract int getFragmentLayoutRes();

    public abstract void initComponent(View contentView, Bundle savedInstanceState);

    public abstract String getThisFragmentName();

    /**
     * 是否有内部fragment.
     *
     * @return
     */
    public abstract boolean hasInnerFragment();

    public Activity getSmartActivity() {
        return mActivity;
    }
}
