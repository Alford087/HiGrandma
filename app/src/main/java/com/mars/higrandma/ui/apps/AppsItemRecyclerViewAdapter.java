package com.mars.higrandma.ui.apps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mars.higrandma.R;
import com.mars.higrandma.core.apps.entity.AppsInfoItem;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;


/**
 * Created by Mars on 2017/3/29.
 */

public class AppsItemRecyclerViewAdapter extends RecyclerView.Adapter<AppsItemRecyclerViewAdapter.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<AppsInfoItem> datalist;

    public AppsItemRecyclerViewAdapter(Context context, List<AppsInfoItem> list) {
        this.mContext = context;
        this.datalist = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, mLayoutInflater.inflate(R.layout.item_apps, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setView(this.datalist.get(position));
    }

    public void update(List<AppsInfoItem> list) {
        this.datalist = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.datalist == null ? 0 : this.datalist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircularImageView avatar;
        private TextView title;
        private Button btnEnter;
        private Context context;

        ViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            avatar = (CircularImageView) view.findViewById(R.id.iv_avatar);
            title = (TextView) view.findViewById(R.id.tv_title);
            btnEnter = (Button) view.findViewById(R.id.btn_enter);
        }

        public void setView(final AppsInfoItem item) {
            title.setText(item.getLabel());
            btnEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(AppsItemRecyclerViewAdapter.class.getSimpleName(), item.getPackageName());
                    Intent intent = context.getPackageManager().getLaunchIntentForPackage(item.getPackageName());
                    context.startActivity(intent);
                }
            });
        }
    }
}