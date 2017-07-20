package com.zoesap.borrowclient.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/19.
 */

public class LoanListAdapter extends BaseQuickAdapter<LoanListItemBean.DataBean.ListBean, BaseViewHolder> {

    public LoanListAdapter(@LayoutRes int layoutResId, @Nullable List<LoanListItemBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoanListItemBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_loan_title, item.getLoan_title())
                .setText(R.id.tv_loanone_info, item.getCount() + "人申请/"
                        + item.getLoan_ftime() + "放款")
                .setText(R.id.tv_loan_interest, item.getLoan_apr() + "%")
                .setText(R.id.tv_limit_deadline, "可贷" + item.getLoan_ilimit()
                        + "~" + item.getLoan_alimit() + "万      " + "期限:"
                        + item.getLoan_ideadline()
                        + "~" + item.getLoan_adeadline() + "期")
                .setText(R.id.tv_loan_type, item.getLoan_type());
    }
}
