package com.zoesap.borrowclient.myloan;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;

import java.util.List;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanPresenter implements MyLoanContract.Presenter {
    private final MyLoanContract.View mLoanView;
    private final Repository mReposity;

    public MyLoanPresenter(MyLoanContract.View mLoanView, Repository mReposity) {
        this.mLoanView = mLoanView;
        this.mReposity = mReposity;
        mLoanView.setPresent(this);
    }

    @Override
    public void start() {
        loadMyLoanList();
    }

    public void loadMyLoanList() {
        mLoanView.showLoadindDialog();
        mReposity.loadMyLoanList(new DataSource.LoadCallback<List<MultiItemEntity>>() {
            @Override
            public void onSuccessful(List<MultiItemEntity> dataList) {
                mLoanView.loadList(dataList);
                mLoanView.loadingDialogDismiss();
            }

            @Override
            public void onFailure() {
                mLoanView.showNetError();
                mLoanView.loadingDialogDismiss();
            }
        });
    }

    @Override
    public void refreshMyLoanList() {
        mReposity.loadMyLoanList(new DataSource.LoadCallback<List<MultiItemEntity>>() {
            @Override
            public void onSuccessful(List<MultiItemEntity> dataList) {
                mLoanView.loadList(dataList);
                mLoanView.refreshProgressDismiss();
            }

            @Override
            public void onFailure() {
                mLoanView.showNetError();
                mLoanView.refreshProgressDismiss();
            }
        });
    }
}
