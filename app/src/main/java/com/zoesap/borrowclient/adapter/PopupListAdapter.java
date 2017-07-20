package com.zoesap.borrowclient.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zoesap.borrowclient.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maoqi on 2017/7/19.
 */

public class PopupListAdapter extends RecyclerView.Adapter<PopupListAdapter.ViewHoler> {
    private List<String> data;
    private Activity activity;
    private AdapterContract.ListItemClickListener listener;

    public PopupListAdapter(Activity activity, List<String> data, AdapterContract.ListItemClickListener listener) {
        this.activity = activity;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_pop_loandeatils, parent, false);
        ViewHoler holer = new ViewHoler(view, viewType);
        return holer;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.tv_Title.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tv_Title;
        @BindView(R.id.ll_content)
        LinearLayout llContent;

        public ViewHoler(View itemView, final int position) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            llContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(position);
                }
            });
        }
    }
}
