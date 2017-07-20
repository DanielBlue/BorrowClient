package com.zoesap.borrowclient.loan;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;
import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;

/**
 * Created by maoqi on 2017/7/18.
 */

public interface LoanContract {
    interface ListLoadMoreListener {
        void loadMoreComplete(LoanListItemBean.DataBean bean);

        void loadMoreEnd(LoanListItemBean.DataBean bean);

        void loadMoreFail();

    }


    interface Presenter extends BasePresenter {
        void loadChooseLoanBeanList();

        void loadLoanBeanList();

        void refreshBeanList(int loantype,
                             int career,
                             int credit,
                             int house);

        void loadMoreBeanList(int loantype,
                              int career,
                              int credit,
                              int house,
                              ListLoadMoreListener listener);
    }

    interface View extends BaseView<Presenter> {
        void initPopupWindow(ChooseLoanTypeBean.DataBean bean);

        void initLoanList(LoanListItemBean.DataBean bean);

        boolean getLoadMoreStatus();

        void setLoadMoreStatus(boolean isLoadMore);

        void setNewData(LoanListItemBean.DataBean bean);

        void setRefreshing(boolean refreshing);

        void setEnableLoadMore(boolean enable);

        void showNoData();

        void showList();

    }
}
