package com.mars.higrandma.core.main;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.mars.higrandma.core.main.entity.ContactInfo;
import com.mars.higrandma.core.main.entity.ContactInfoItem;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mars on 2017/5/31.
 */

public class ContactListModel {

    private List<ContactInfoItem> getAllContact(Context context) throws Exception {
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        List<ContactInfoItem> dataList = new ArrayList<>();
        ContactInfoItem item;
        try {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
                int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                item = new ContactInfoItem();
                item.setName(cursor.getString(nameFieldColumnIndex));
                item.setNumber(cursor.getString(numberFieldColumnIndex));
                dataList.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            cursor.close();
        }
        return dataList;
    }

    public Observable<ContactInfo> bind(final Context context) {
        return Observable
                .create(new Observable.OnSubscribe<ContactInfo>() {
                    @Override
                    public void call(final Subscriber<? super ContactInfo> subscriber) {
                        try {
                            List<ContactInfoItem> contactItems = getAllContact(context);
                            subscriber.onNext(new ContactInfo(contactItems));
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}
