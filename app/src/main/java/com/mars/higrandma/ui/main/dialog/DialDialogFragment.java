package com.mars.higrandma.ui.main.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.mars.higrandma.R;

/**
 * Created by mars on 2017/5/31.
 */

public class DialDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String ARG_NAME = "ARG_NAME";
    private static final String ARG_NUMBER = "ARG_NUMBER";
    private static final String ARG_AVATAR = "ARG_AVATAR";
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvTel;


    private String name;
    private String number;
    private String avatar;

    public DialDialogFragment() {
    }

    public static DialDialogFragment newInstance(String name, String number, String avatar) {
        DialDialogFragment fragment = new DialDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_NUMBER, number);
        args.putString(ARG_AVATAR, avatar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_dial, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onViewCreated(view, savedInstanceState);
        name = getArguments().getString(ARG_NAME, "");
        number = getArguments().getString(ARG_NUMBER, "");
        avatar = getArguments().getString(ARG_AVATAR, "");

        ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvTel = (TextView) view.findViewById(R.id.tv_tel);

        tvName.setText(name);
        tvTel.setText(number);
        view.findViewById(R.id.btn_message).setOnClickListener(this);
        view.findViewById(R.id.btn_dial).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_message:
                Uri uri = Uri.parse("smsto:" + number);
                Intent intentFinalMessage = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentFinalMessage);
                break;
            case R.id.btn_dial:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                startActivity(intent);
                break;
        }
        dismiss();
    }
}
