package com.mars.higrandma;

import android.app.Application;

import mars.core.CrashHandler;
import rx.Subscription;

/**
 * Created by mars on 2017/5/27.
 */

public class SmartApplication extends Application {
    private String NO = "869585016928913";
    Subscription subscription;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
//        subscription = new DeviceModel()
//                .getObservable1(getApplicationContext(), NO)
//                .subscribe(new Subscriber<Boolean>() {
//                    @Override
//                    public void onCompleted() {
//                        subscription = null;
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        SmartDebugUtils.logd("", e.getMessage());
//                        System.exit(0);
//                    }
//
//                    @Override
//                    public void onNext(Boolean aBoolean) {
//
//                    }
//                });
    }
}
