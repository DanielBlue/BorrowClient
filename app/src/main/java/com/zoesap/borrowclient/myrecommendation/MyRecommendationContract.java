package com.zoesap.borrowclient.myrecommendation;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;
import com.zoesap.borrowclient.data.bean.MyRecommendBean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface MyRecommendationContract {
    interface Presenter extends BasePresenter {
        void refresh();
    }

    interface View extends BaseView<Presenter> {
        void refreshProgressDismiss();

        void updateRecommendCode(String code);

        void showEmptyList();

        void loadRecommedList(List<MyRecommendBean.DataBean.DatasBean> dataList);

        void updateRecommendNum(int num);

    }
}
