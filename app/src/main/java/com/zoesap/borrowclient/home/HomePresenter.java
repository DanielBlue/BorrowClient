package com.zoesap.borrowclient.home;


import android.util.Log;

import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

import java.util.List;

/**
 * Created by maoqi on 2017/7/18.
 */

public class HomePresenter implements HomeContract.Presenter {
    private final HomeContract.View mHomeView;
    private final Repository mDataReposity;

    public HomePresenter(HomeContract.View mHomeView, Repository mDataReposity) {
        this.mHomeView = NullUtils.checkNotNull(mHomeView);
        this.mDataReposity = NullUtils.checkNotNull(mDataReposity);
        mHomeView.setPresent(this);
    }

    @Override
    public void start() {
        loadData();
    }

    private void loadData() {
        Log.e("LoanPresenter","loadData(LoanPresenter.java:30)"+"load data");
        mHomeView.showLoadindDialog();
        mDataReposity.getRecommendedLoanItem(new DataSource.LoadCallback<List<LoanRecommendItemBean.DataBean.ListBean>>() {
            @Override
            public void onSuccessful(List<LoanRecommendItemBean.DataBean.ListBean> listBeen) {
                mHomeView.refreshList(listBeen);
                mHomeView.loadingDialogDismiss();
            }

            @Override
            public void onFailure() {
                mHomeView.showNetError();
                mHomeView.loadingDialogDismiss();
            }
        });
    }
}
