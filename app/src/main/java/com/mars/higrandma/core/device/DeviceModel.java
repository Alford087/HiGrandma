package com.mars.higrandma.core.device;

import android.content.Context;
import android.telephony.TelephonyManager;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alford on 2017/5/31.
 */

public class DeviceModel {
    public Observable<String> getObservable0(final Context context) {
        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(final Subscriber<? super String> subscriber) {
                        try {
                            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                            String tel = tm.getLine1Number();//手机号码
                            subscriber.onNext(tel);
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> getObservable1(final Context context, final String uniqueID) {
        return Observable
                .create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(final Subscriber<? super Boolean> subscriber) {
                        try {
                            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                            String deviceid = tm.getDeviceId();
                            if (uniqueID.equals(deviceid)) {
                                subscriber.onNext(true);
                            } else {
                                throw new Exception("设备不符...");
                            }
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
