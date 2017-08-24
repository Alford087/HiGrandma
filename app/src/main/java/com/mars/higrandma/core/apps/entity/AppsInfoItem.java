package com.mars.higrandma.core.apps.entity;

import android.support.annotation.Keep;

/**
 * Created by mars on 2017/5/31.
 */

@Keep
public class AppsInfoItem {

    private String label; // 存放应用程序名

    private String packageName; // 存放应用程序包名

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
