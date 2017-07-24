package com.zoesap.borrowclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.MyLoanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanListAdapter extends RecyclerView.Adapter {
    private List<MultiItemEntity> dataList;
    private Context context;
    private MyLoanBean.DataBean.ListBean listBean;
    private MyLoanBean.DataBean.RecommendBean recommendBean;
    private RecyclerView.ViewHolder viewHolder;

    public MyLoanListAdapter(Context context, List<MultiItemEntity> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case AdapterContract.MyLoanMultiItem.LOAN_LIST:
                View loanView = LayoutInflater.from(context).inflate(R.layout.item_my_loan, null);
                viewHolder = new MyLoanViewHolder(loanView);
            case AdapterContract.MyLoanMultiItem.RECOMMEND_LOAN_LIST:
                View recommendLoanView = LayoutInflater.from(context).inflate(R.layout.item_loan_recommend_list, null);
                viewHolder = new RecommendLoanViewHolder(recommendLoanView);
            case AdapterContract.MyLoanMultiItem.LOAN_LIST_EMPTY:
                View myLoanEmptyView = LayoutInflater.from(context).inflate(R.layout.item_my_loan_empty, null);
                viewHolder = new MyLoanEmptyViewHolder(myLoanEmptyView);
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position) instanceof MyLoanBean.DataBean.ListBean) {
            return AdapterContract.MyLoanMultiItem.LOAN_LIST;
        } else if (dataList.get(position) instanceof MyLoanBean.DataBean.RecommendBean) {
            return AdapterContract.MyLoanMultiItem.RECOMMEND_LOAN_LIST;
        } else if (dataList.get(position) instanceof MyLoanBean.DataBean.EmptyBean) {
            return AdapterContract.MyLoanMultiItem.LOAN_LIST_EMPTY;
        }
//        else if (dataList.get(position) instanceof MyLoanBean.DataBean.EmptyBean){
//
//        }
        return -1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyLoanViewHolder) {
            listBean = (MyLoanBean.DataBean.ListBean) dataList.get(position);
            ((MyLoanViewHolder) holder).tvLoanTitle.setText(listBean.getLoan_title());
            ((MyLoanViewHolder) holder).tvLoanItemTime.setText("申请时间 : " + listBean.getApply_time());
            ((MyLoanViewHolder) holder).tvLoanItemLimit.setText("申请金额 : " + listBean.getApply_money());
            ((MyLoanViewHolder) holder).tvLoanItemRate.setText("利率 : " + listBean.getLoan_apr() + "%");
            ((MyLoanViewHolder) holder).tvItemPhonenum.setText("联系电话 : " + listBean.getTel());
            int Intstatsnum = Integer.parseInt(listBean.getState());
            if (Intstatsnum >= 2) {
                ((MyLoanViewHolder) holder).tvItemCancel.setVisibility(View.GONE);
            } else {
                ((MyLoanViewHolder) holder).tvItemCancel.setVisibility(View.VISIBLE);
            }

            switch (Intstatsnum) {
                case 0:
                    ((MyLoanViewHolder) holder).ivProgress.setImageResource(R.drawable.dcsone);
                    break;
                case 1:
                    ((MyLoanViewHolder) holder).ivProgress.setImageResource(R.drawable.slztwo);
                    break;
                case 2:
                    ((MyLoanViewHolder) holder).ivProgress.setImageResource(R.drawable.dqythree);
                    break;
                case 3:
                    ((MyLoanViewHolder) holder).ivProgress.setImageResource(R.drawable.dshfor);
                    break;
                case 4:
                    ((MyLoanViewHolder) holder).ivProgress.setImageResource(R.drawable.dfkfive);
                    break;
                case 5:
                    ((MyLoanViewHolder) holder).ivProgress.setImageResource(R.drawable.fkcgsix);
                    break;
            }

        } else if (holder instanceof RecommendLoanViewHolder) {
            recommendBean = (MyLoanBean.DataBean.RecommendBean) dataList.get(position);
            ((RecommendLoanViewHolder) holder).tvLoanTitle.setText(recommendBean.getLoan_title());
            ((RecommendLoanViewHolder) holder).tvLoanLimit.setText(recommendBean.getLoan_ilimit()
                    + "~" + recommendBean.getLoan_alimit());
            ((RecommendLoanViewHolder) holder).tvApprovalTimeLimit
                    .setText(recommendBean.getLoan_ftime());
            ((RecommendLoanViewHolder) holder).tvMonthInterest.setText("月利息"
                    + recommendBean.getLoan_apr() + "%");
            ((RecommendLoanViewHolder) holder).tvLoanTimeLimit.setText("贷款期限"
                    + recommendBean.getLoan_ideadline()
                    + "~" + recommendBean.getLoan_adeadline() + "个月");
        } else if (holder instanceof MyLoanEmptyViewHolder) {


        } else if (holder instanceof RecommendLoanHeaderViewHolder) {


        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

//    public MyLoanListAdapter(List<MultiItemEntity> data) {
//        super(data);
//        addItemType(AdapterContract.MyLoanMultiItem.LOAN_LIST, R.layout.item_my_loan);
//        addItemType(AdapterContract.MyLoanMultiItem.LOAN_LIST_EMPTY, R.layout.item_my_loan_empty);
//        addItemType(AdapterContract.MyLoanMultiItem.BOTTOM_LIST, R.layout.item_loan_recommend_list);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
//        switch (helper.getItemViewType()) {
//            case AdapterContract.MyLoanMultiItem.LOAN_LIST:
//                MyLoanBean.DataBean.ListBean bean = (MyLoanBean.DataBean.ListBean) item;
//                helper.setText(R.id.tv_loan_title, bean.getLoan_title())
//                        .setText(R.id.tv_loan_item_time, "申请时间 : " + bean.getApply_time())
//                        .setText(R.id.tv_loan_item_limit, "申请金额 : " + bean.getApply_money())
//                        .setText(R.id.tv_loan_item_rate, "利率 : " + bean.getLoan_apr() + "%")
//                        .setText(R.id.tv_item_phonenum, "联系电话 : " + bean.getTel());
//
//                int Intstatsnum = Integer.parseInt(bean.getState());
//                if (Intstatsnum >= 2) {
//                    helper.setVisible(R.id.tv_item_cancel, false);
//                } else {
//                    helper.setVisible(R.id.tv_item_cancel, true);
//                }
//
//                switch (Intstatsnum) {
//                    case 0:
//                        helper.setImageResource(R.id.tv_item_cancel, R.drawable.dcsone);
//                        break;
//                    case 1:
//                        helper.setImageResource(R.id.tv_item_cancel, R.drawable.slztwo);
//                        break;
//                    case 2:
//                        helper.setImageResource(R.id.tv_item_cancel, R.drawable.dqythree);
//                        break;
//                    case 3:
//                        helper.setImageResource(R.id.tv_item_cancel, R.drawable.dshfor);
//                        break;
//                    case 4:
//                        helper.setImageResource(R.id.tv_item_cancel, R.drawable.dfkfive);
//                        break;
//                    case 5:
//                        helper.setImageResource(R.id.tv_item_cancel, R.drawable.fkcgsix);
//                        break;
//                }
//                helper.addOnClickListener(R.id.tv_item_cancel);
//
//                break;
//            case AdapterContract.MyLoanMultiItem.LOAN_LIST_EMPTY:
//                setEmptyView(R.layout.item_my_loan_empty);
//                break;
//            case AdapterContract.MyLoanMultiItem.BOTTOM_LIST:
//                MyLoanBean.DataBean.RecommendBean recommendBean = (MyLoanBean.DataBean.RecommendBean) item;
//
//                helper.setText(R.id.tv_loan_title, recommendBean.getLoan_title())
//                        .setText(R.id.tv_loan_limit, recommendBean.getLoan_ilimit()
//                                + "~" + recommendBean.getLoan_alimit())
//                        .setText(R.id.tv_approval_time_limit, recommendBean.getLoan_ftime())
//                        .setText(R.id.tv_month_interest, "月利息"
//                                + recommendBean.getLoan_apr() + "%")
//                        .setText(R.id.tv_loan_time_limit, "贷款期限"
//                                + recommendBean.getLoan_ideadline()
//                                + "~" + recommendBean.getLoan_adeadline() + "个月");
//                break;
//        }
//    }


    static class MyLoanViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_loan_title)
        TextView tvLoanTitle;
        @BindView(R.id.tv_loan_item_time)
        TextView tvLoanItemTime;
        @BindView(R.id.tv_loan_item_limit)
        TextView tvLoanItemLimit;
        @BindView(R.id.tv_loan_item_rate)
        TextView tvLoanItemRate;
        @BindView(R.id.tv_item_phonenum)
        TextView tvItemPhonenum;
        @BindView(R.id.tv_item_cancel)
        TextView tvItemCancel;
        @BindView(R.id.iv_progress)
        ImageView ivProgress;

        public MyLoanViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class RecommendLoanViewHolder extends RecyclerView.ViewHolder {
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

        public RecommendLoanViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyLoanEmptyViewHolder extends RecyclerView.ViewHolder {
        public MyLoanEmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class RecommendLoanHeaderViewHolder extends RecyclerView.ViewHolder {
        public RecommendLoanHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
