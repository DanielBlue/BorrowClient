package com.zoesap.borrowclient.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.MyLoanBean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanListAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyLoanListAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(AdapterContract.MyLoanMultiItem.LOAN_LIST,R.layout.item_my_loan);
        addItemType(AdapterContract.MyLoanMultiItem.RECOMMEND_LOAN_LIST,R.layout.item_loan_recommend_list);
        addItemType(AdapterContract.MyLoanMultiItem.RECOMMEND_LOAN_LIST_HEADER,R.layout.item_loan_recommend_list_header);
        addItemType(AdapterContract.MyLoanMultiItem.LOAN_LIST_EMPTY,R.layout.item_my_loan_empty);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case AdapterContract.MyLoanMultiItem.LOAN_LIST:
                MyLoanBean.DataBean.ListBean bean = (MyLoanBean.DataBean.ListBean) item;
                helper.setText(R.id.tv_loan_title, bean.getLoan_title())
                        .setText(R.id.tv_loan_item_time, "申请时间 : " + bean.getApply_time())
                        .setText(R.id.tv_loan_item_limit, "申请金额 : " + bean.getApply_money())
                        .setText(R.id.tv_loan_item_rate, "利率 : " + bean.getLoan_apr() + "%")
                        .setText(R.id.tv_item_phonenum, "联系电话 : " + bean.getTel());

                int Intstatsnum = Integer.parseInt(bean.getState());
                if (Intstatsnum >= 2) {
                    helper.setVisible(R.id.tv_item_cancel, false);
                } else {
                    helper.setVisible(R.id.tv_item_cancel, true);
                }

                switch (Intstatsnum) {
                    case 0:
                        helper.setImageResource(R.id.iv_progress, R.drawable.dcsone);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv_progress, R.drawable.slztwo);
                        break;
                    case 2:
                        helper.setImageResource(R.id.iv_progress, R.drawable.dqythree);
                        break;
                    case 3:
                        helper.setImageResource(R.id.iv_progress, R.drawable.dshfor);
                        break;
                    case 4:
                        helper.setImageResource(R.id.iv_progress, R.drawable.dfkfive);
                        break;
                    case 5:
                        helper.setImageResource(R.id.iv_progress, R.drawable.fkcgsix);
                        break;
                }
                helper.addOnClickListener(R.id.tv_item_cancel);

                break;
            case AdapterContract.MyLoanMultiItem.RECOMMEND_LOAN_LIST:
                MyLoanBean.DataBean.RecommendBean recommendBean = (MyLoanBean.DataBean.RecommendBean) item;

                helper.setText(R.id.tv_loan_title, recommendBean.getLoan_title())
                        .setText(R.id.tv_loan_limit, recommendBean.getLoan_ilimit()
                                + "~" + recommendBean.getLoan_alimit())
                        .setText(R.id.tv_approval_time_limit, recommendBean.getLoan_ftime())
                        .setText(R.id.tv_month_interest, "月利息"
                                + recommendBean.getLoan_apr() + "%")
                        .setText(R.id.tv_loan_time_limit, "贷款期限"
                                + recommendBean.getLoan_ideadline()
                                + "~" + recommendBean.getLoan_adeadline() + "个月")
                        .addOnClickListener(R.id.rl_content);
                break;
        }
    }

}
