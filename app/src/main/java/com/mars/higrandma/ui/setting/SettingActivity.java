package com.mars.higrandma.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.provider.Settings;

import com.mars.higrandma.R;
import com.mars.higrandma.ui.apps.AppListActivity;
import com.mars.higrandma.ui.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by mars on 2017/5/27.
 */

public class SettingActivity extends BaseActivity {

    @Override
    public int getContentViewID() {
        return R.layout.activity_app_settings;
    }

    public static void startThisActivity(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    @OnClick(R.id.btn_app_list)
    public void goAppListActivity() {
        AppListActivity.startThisActivity(this);
    }


    @OnClick(R.id.btn_system_setting)
    public void startSystemSetting() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    @OnClick(R.id.btn_alarm)
    public void startAlarm() {
        Intent alarmas = new Intent(AlarmClock.ACTION_SET_ALARM);
        startActivity(alarmas);
    }

    @OnClick(R.id.btn_camera)
    public void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    @OnClick(R.id.btn_calendar)
    public void startCalendar() {
        Intent intent = getApplication().getPackageManager().getLaunchIntentForPackage("com.android.calendar");
        startActivity(intent);
    }

    @OnClick(R.id.btn_contact)
    public void startContact() {
        Intent intent = getApplication().getPackageManager().getLaunchIntentForPackage("com.android.contacts");
        startActivity(intent);
    }

    @OnClick(R.id.btn_file)
    public void startGallery() {
        Intent intent = getApplication().getPackageManager().getLaunchIntentForPackage("zte.com.cn.filer");
        startActivity(intent);
    }

}
