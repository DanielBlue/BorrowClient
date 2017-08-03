package com.zoesap.borrowclient.home;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/18.
 */

interface HomeContract {
    interface View extends CoreBaseView<Presenter> {
        void refreshList(List<LoanRecommendItemBean.DataBean.ListBean> list);
    }

    interface Presenter extends CoreBasePresenter {
    }
}
