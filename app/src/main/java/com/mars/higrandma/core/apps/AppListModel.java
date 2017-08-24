package com.mars.higrandma.core.apps;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.mars.higrandma.core.apps.entity.AppInfo;
import com.mars.higrandma.core.apps.entity.AppsInfoItem;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mars on 2017/5/31.
 */

public class AppListModel {

    private List<PackageInfo> getApps(Context context, boolean isAllowSystemApps) {
        List<PackageInfo> apps = new ArrayList<>();
        PackageManager pManager = context.getPackageManager();
        // 获取手机内所有应用
        PackageInfo pak;
        List<PackageInfo> packList = pManager.getInstalledPackages(0);
        for (int i = 0; i < packList.size(); i++) {
            pak = packList.get(i);
            // 判断是否为非系统预装的应用程序，这里还可以添加系统自带的，这里就先不添加了，如果有需要可以自己添加if()里的值如果<=0则为自己装的程序，否则为系统工程自带
            if (isAllowSystemApps && (pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {//添加自己已经安装的应用程序
                apps.add(pak);
            }
        }
        return apps;
    }

    public Observable<AppInfo> bind(final Context context, final boolean isAllowSystemApps) {
        return Observable
                .create(new Observable.OnSubscribe<AppInfo>() {
                    @Override
                    public void call(final Subscriber<? super AppInfo> subscriber) {
                        try {
                            List<PackageInfo> packageInfos = getApps(context, isAllowSystemApps);
                            List<AppsInfoItem> apps = new ArrayList<>();
                            AppsInfoItem item;
                            for (PackageInfo p : packageInfos) {
                                item = new AppsInfoItem();
                                // 设置应用程序名字
                                item.setLabel(context.getPackageManager().getApplicationLabel(p.applicationInfo).toString());
                                // 设置应用程序的包名
                                item.setPackageName(p.applicationInfo.packageName);
                                if (!p.applicationInfo.packageName.equals(context.getPackageName())) {
                                    apps.add(item);
                                }
                            }
                            subscriber.onNext(new AppInfo(apps));
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}
