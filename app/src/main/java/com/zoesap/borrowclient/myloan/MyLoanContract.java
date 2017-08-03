package com.zoesap.borrowclient.myloan;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

import java.util.List;

/**
 * Created by maoqi on 2017/7/21.
 */

public interface MyLoanContract {
    interface Presenter extends CoreBasePresenter {
        void refreshMyLoanList();

        void cancelMyLoanRequest(String id);
    }

    interface View extends CoreBaseView<Presenter> {
        void loadList(List<MultiItemEntity> dataList);

        void refreshProgressDismiss();
    }
}
