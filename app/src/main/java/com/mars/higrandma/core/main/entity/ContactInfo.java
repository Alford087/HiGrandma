package com.mars.higrandma.core.main.entity;

import android.support.annotation.Keep;

import java.util.List;

/**
 * Created by mars on 2017/5/31.
 */
@Keep
public class ContactInfo {

    public List<ContactInfoItem> list;

    public ContactInfo(List<ContactInfoItem> list) {
        this.list = list;
    }
}
