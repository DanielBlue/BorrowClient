package com.zoesap.borrowclient.myloan;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;
import com.zoesap.borrowclient.data.bean.MyLoanBean;

/**
 * Created by maoqi on 2017/7/21.
 */

public interface MyLoanContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void updateList(MyLoanBean.DataBean bean);
    }
}
