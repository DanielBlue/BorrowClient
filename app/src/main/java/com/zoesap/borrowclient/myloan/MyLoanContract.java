package com.zoesap.borrowclient.myloan;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

import java.util.List;

/**
 * Created by maoqi on 2017/7/21.
 */

public interface MyLoanContract {
    interface Presenter extends BasePresenter {
        void refreshMyLoanList();

        void cancelMyLoanRequest(String id);
    }

    interface View extends BaseView<Presenter> {
        void loadList(List<MultiItemEntity> dataList);

        void refreshProgressDismiss();
    }
}
