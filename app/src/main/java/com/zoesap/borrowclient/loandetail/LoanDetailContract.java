package com.zoesap.borrowclient.loandetail;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;

/**
 * Created by maoqi on 2017/7/20.
 */

public interface LoanDetailContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void updateView(LoanDetailBean.DataBean dataBean);
    }
}
