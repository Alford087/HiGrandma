package com.mars.higrandma.core.main.entity;

import android.support.annotation.Keep;

/**
 * Created by mars on 2017/5/31.
 */
@Keep
public class ContactInfoItem {
    String name;
    String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
