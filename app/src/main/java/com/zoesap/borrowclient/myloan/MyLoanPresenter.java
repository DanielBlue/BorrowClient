package com.zoesap.borrowclient.myloan;

import android.support.annotation.NonNull;
import android.util.Log;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.BaseBeanWrapper;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import pers.maoqi.core.util.NullUtils;

import java.util.List;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanPresenter implements MyLoanContract.Presenter {
    private final MyLoanContract.View mMyLoanView;
    private final Repository mReposity;

    public MyLoanPresenter(@NonNull MyLoanContract.View mMyLoanView, @NonNull Repository mReposity) {
        this.mMyLoanView = NullUtils.checkNotNull(mMyLoanView);
        this.mReposity = NullUtils.checkNotNull(mReposity);
        mMyLoanView.setPresent(this);
    }

    @Override
    public void start() {
        loadMyLoanList();
    }

    public void loadMyLoanList() {
        mMyLoanView.showLoadindDialog();
        mReposity.loadMyLoanList(new DataSource.LoadCallback<List<MultiItemEntity>>() {
            @Override
            public void onSuccessful(List<MultiItemEntity> dataList) {
                mMyLoanView.loadList(dataList);
                mMyLoanView.loadingDialogDismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("MyLoanPresenter","onFailure(MyLoanPresenter.java:44)"+t.getMessage());
                mMyLoanView.toastInfo(R.string.net_error);
                mMyLoanView.loadingDialogDismiss();
            }
        });
    }

    @Override
    public void refreshMyLoanList() {
        mReposity.loadMyLoanList(new DataSource.LoadCallback<List<MultiItemEntity>>() {
            @Override
            public void onSuccessful(List<MultiItemEntity> dataList) {
                mMyLoanView.loadList(dataList);
                mMyLoanView.refreshProgressDismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                mMyLoanView.toastInfo(R.string.net_error);
                mMyLoanView.refreshProgressDismiss();
            }
        });
    }

    @Override
    public void cancelMyLoanRequest(String id) {
        mMyLoanView.showLoadindDialog();
        mReposity.cancelMyLoanRequest(id, new DataSource.LoadCallback<BaseBeanWrapper>() {
            @Override
            public void onSuccessful(BaseBeanWrapper baseBeanWrapper) {
                if (baseBeanWrapper.getCode() == 10000) {
                    loadMyLoanList();
                }
                mMyLoanView.toastInfo(baseBeanWrapper.getInfo());
            }

            @Override
            public void onFailure(Throwable t) {
                mMyLoanView.toastInfo(R.string.net_error);
                mMyLoanView.loadingDialogDismiss();
            }
        });
    }
}
