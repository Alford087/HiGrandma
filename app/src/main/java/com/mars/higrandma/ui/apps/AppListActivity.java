package com.mars.higrandma.ui.apps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mars.higrandma.R;
import com.mars.higrandma.core.apps.AppListModel;
import com.mars.higrandma.core.apps.entity.AppInfo;
import com.mars.higrandma.core.apps.entity.AppsInfoItem;
import com.mars.higrandma.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.InjectView;
import rx.Subscriber;
import rx.Subscription;

public class AppListActivity extends BaseActivity {

    @InjectView(R.id.rv_list)
    RecyclerView recyclerView;

    private Subscription subscription;
    private AppListModel viewModel = new AppListModel();

    private AppsItemRecyclerViewAdapter mAppsItemRecyclerViewAdapter;

    @Override
    public int getContentViewID() {
        return R.layout.activity_app_list;
    }

    public static void startThisActivity(Context context) {
        context.startActivity(new Intent(context, AppListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        mAppsItemRecyclerViewAdapter = new AppsItemRecyclerViewAdapter(AppListActivity.this, new ArrayList<AppsInfoItem>());
        recyclerView.setAdapter(mAppsItemRecyclerViewAdapter);
        subscription = viewModel
                .bind(this, true)
                .subscribe(new Subscriber<AppInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(AppListActivity.class.getSimpleName(), e.getMessage());
                    }

                    @Override
                    public void onNext(AppInfo appInfo) {
                        mAppsItemRecyclerViewAdapter.update(appInfo.list);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}


