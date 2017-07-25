package com.zoesap.borrowclient.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.MyRecommendBean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/25.
 */

public class MyRecommendListAdapter extends BaseQuickAdapter<MyRecommendBean.DataBean.DatasBean, BaseViewHolder> {

    public MyRecommendListAdapter(@LayoutRes int layoutResId, @Nullable List<MyRecommendBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyRecommendBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_loan_reccomend_item_time, item.getApply_time())
                .setText(R.id.tv_loan_reccomend_item_money, item.getMoney() + "万元")
                .setText(R.id.tv_reccomend_send_title, item.getApply_for());

        String statsnum = item.getState();
        int Intstatsnum = Integer.parseInt(statsnum);
        if (Intstatsnum == 0) {
            helper.setImageResource(R.id.iv_loan_recommd, R.drawable.dcsone);
        } else if (Intstatsnum == 1) {
            helper.setImageResource(R.id.iv_loan_recommd, R.drawable.slztwo);
        } else if (Intstatsnum == 2) {
            helper.setImageResource(R.id.iv_loan_recommd, R.drawable.dqythree);
        } else if (Intstatsnum == 3) {
            helper.setImageResource(R.id.iv_loan_recommd, R.drawable.dshfor);
        } else if (Intstatsnum == 4) {
            helper.setImageResource(R.id.iv_loan_recommd, R.drawable.dfkfive);
        } else if (Intstatsnum == 5) {
            helper.setImageResource(R.id.iv_loan_recommd, R.drawable.fkcgsix);
        }
    }
}
