package com.mars.higrandma.ui.main;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mars.higrandma.R;
import com.mars.higrandma.core.main.entity.ContactInfoItem;
import com.mars.higrandma.ui.main.dialog.DialDialogFragment;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;


/**
 * Created by Mars on 2017/3/29.
 */

public class DestopItemRecyclerViewAdapter extends RecyclerView.Adapter<DestopItemRecyclerViewAdapter.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<ContactInfoItem> dataList;

    public DestopItemRecyclerViewAdapter(Context context, List<ContactInfoItem> data) {
        this.dataList = data;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mContext, mLayoutInflater.inflate(R.layout.item_destop, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setView(this.dataList.get(position));
    }


    @Override
    public int getItemCount() {
        return this.dataList == null ? 0 : this.dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircularImageView avatar;
        private TextView title;
        private Context ctx;

        ViewHolder(Context ctx, View view) {
            super(view);
            this.ctx = ctx;
            avatar = (CircularImageView) view.findViewById(R.id.iv_avatar);
            title = (TextView) view.findViewById(R.id.tv_title);
        }

        public void setView(final ContactInfoItem item) {
            title.setText(item.getName());
            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ctx instanceof FragmentActivity)
                        DialDialogFragment.newInstance(item.getName(), item.getNumber(), "").show(((FragmentActivity) ctx).getSupportFragmentManager(), "");
                }
            });
        }
    }

    public void update(List<ContactInfoItem> data) {
        this.dataList = data;
        this.notifyDataSetChanged();
    }
}