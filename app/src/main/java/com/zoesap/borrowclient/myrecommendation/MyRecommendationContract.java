package com.zoesap.borrowclient.myrecommendation;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;
import com.zoesap.borrowclient.data.bean.MyRecommendBean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface MyRecommendationContract {
    interface Presenter extends CoreBasePresenter {
        void refresh();
    }

    interface View extends CoreBaseView<Presenter> {
        void refreshProgressDismiss();

        void updateRecommendCode(String code);

        void showEmptyList();

        void loadRecommedList(List<MyRecommendBean.DataBean.DatasBean> dataList);

        void updateRecommendNum(int num);

    }
}
