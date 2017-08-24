package com.mars.higrandma.ui.main;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mars.higrandma.R;
import com.mars.higrandma.core.main.ContactListModel;
import com.mars.higrandma.core.main.entity.ContactInfo;
import com.mars.higrandma.core.main.entity.ContactInfoItem;
import com.mars.higrandma.ui.base.BaseFragment;
import com.mars.higrandma.ui.main.dialog.DialDialogFragment;

import java.util.ArrayList;

import butterknife.InjectView;
import rx.Subscriber;

public class SmartAppFragment extends BaseFragment {

    @InjectView(R.id.rv_main)
    RecyclerView recyclerView;

    private ContactListModel mContactListModel = new ContactListModel();

    private DestopItemRecyclerViewAdapter mainAdapter;

    public SmartAppFragment() {
        // Required empty public constructor
    }

    @Override
    public int getFragmentLayoutRes() {
        return R.layout.fragment_smart_app;
    }

    @Override
    public void initComponent(View contentView, Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new GridLayoutManager(getSmartActivity(), 2));//这里用线性显示 类似于listview
        mainAdapter = new DestopItemRecyclerViewAdapter(getSmartActivity(), new ArrayList<ContactInfoItem>());
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mContactListModel
                .bind(getContext())
                .subscribe(new Subscriber<ContactInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ContactInfo contactInfo) {
                        mainAdapter.update(contactInfo.list);
                    }
                });
    }

    @Override
    public String getThisFragmentName() {
        return null;
    }

    @Override
    public boolean hasInnerFragment() {
        return false;
    }


}
