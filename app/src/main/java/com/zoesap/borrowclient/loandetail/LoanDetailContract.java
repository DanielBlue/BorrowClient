package com.zoesap.borrowclient.loandetail;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;

/**
 * Created by maoqi on 2017/7/20.
 */

public interface LoanDetailContract {
    interface Presenter extends CoreBasePresenter {
    }

    interface View extends CoreBaseView<Presenter> {
        void updateView(LoanDetailBean.DataBean dataBean);
    }
}
