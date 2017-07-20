package com.zoesap.borrowclient.home;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/18.
 */

interface HomeContract {
    interface View extends BaseView<Presenter> {
        void refreshList(List<LoanRecommendItemBean.DataBean.ListBean> list);
    }

    interface Presenter extends BasePresenter {
    }
}
