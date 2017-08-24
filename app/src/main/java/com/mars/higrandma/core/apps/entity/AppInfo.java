package com.mars.higrandma.core.apps.entity;

import android.support.annotation.Keep;

import java.util.List;

/**
 * Created by mars on 2017/5/31.
 */

@Keep
public class AppInfo {

    public List<AppsInfoItem> list;

    public AppInfo(List<AppsInfoItem> list) {
        this.list = list;
    }
}
