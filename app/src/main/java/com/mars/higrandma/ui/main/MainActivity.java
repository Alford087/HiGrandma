package com.mars.higrandma.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.mars.higrandma.R;
import com.mars.higrandma.core.device.DeviceModel;
import com.mars.higrandma.ui.base.BaseActivity;
import com.mars.higrandma.ui.main.dialog.ClockDialogFragment;
import com.mars.higrandma.ui.setting.SettingActivity;

import rx.Subscriber;

public class MainActivity extends BaseActivity {
    DeviceModel mDeviceModel = new DeviceModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        initTitle();
    }

    public void initTitle() {
        mDeviceModel
                .getObservable0(getApplication())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            getSupportActionBar().setTitle("本机号码:" + s);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_flashlight:
                ClockDialogFragment.newInstance().show(getSupportFragmentManager(), "");
                break;
            case R.id.action_app_setting:
                SettingActivity.startThisActivity(this);
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
