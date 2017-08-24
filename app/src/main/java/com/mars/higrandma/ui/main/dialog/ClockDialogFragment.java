package com.mars.higrandma.ui.main.dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


import com.mars.higrandma.R;

/**
 * Created by mars on 2017/5/31.
 */

public class ClockDialogFragment extends DialogFragment {

    public ClockDialogFragment() {
    }
    public static ClockDialogFragment newInstance() {
        ClockDialogFragment fragment = new ClockDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_clock, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onViewCreated(view, savedInstanceState);
    }


}
