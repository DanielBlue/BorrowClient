package com.zoesap.borrowclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/19.
 */

public class LoanRecommendListAdapter extends RecyclerView.Adapter<LoanRecommendListAdapter.ViewHolder> {
    private List<LoanRecommendItemBean.DataBean.ListBean> data;
    private Context context;
    private static AdapterContract.ListItemClickListener listener;

    public LoanRecommendListAdapter(List<LoanRecommendItemBean.DataBean.ListBean> data, Context context) {
        this.data = NullUtils.checkNotNull(data);
        this.context = NullUtils.checkNotNull(context);
    }

    public void setListItemClickListener(AdapterContract.ListItemClickListener listener) {
        this.listener = NullUtils.checkNotNull(listener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_loan_recommend_list, null);
        ViewHolder holder = new ViewHolder(view,viewType);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvLoanTitle.setText(data.get(position).getLoan_title());
        holder.tvLoanLimit.setText(data.get(position).getLoan_ilimit()
                + "~" + data.get(position).getLoan_alimit());
        holder.tvApprovalTimeLimit.setText(data.get(position).getLoan_ftime());
        holder.tvMonthInterest.setText("月利息" + data.get(position).getLoan_apr() + "%");
        holder.tvLoanTimeLimit.setText("贷款期限" + data.get(position).getLoan_ideadline()
                + "~" + data.get(position).getLoan_adeadline() + "个月");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_content)
        RelativeLayout rlContent;
        @BindView(R.id.iv_loan_icon)
        ImageView ivLoanIcon;
        @BindView(R.id.tv_loan_title)
        TextView tvLoanTitle;
        @BindView(R.id.tv_loan_limit)
        TextView tvLoanLimit;
        @BindView(R.id.tv_approval_time_limit)
        TextView tvApprovalTimeLimit;
        @BindView(R.id.tv_month_interest)
        TextView tvMonthInterest;
        @BindView(R.id.tv_loan_time_limit)
        TextView tvLoanTimeLimit;

        public ViewHolder(View itemView, final int position) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rlContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClickListener(position);
                    }
                }
            });
        }
    }
}
