package com.zoesap.borrowclient.myloan;

import com.zoesap.borrowclient.data.source.Repository;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanPresenter implements MyLoanContract.Presenter {
    private final MyLoanContract.View mLoanView;
    private final Repository mReposity;

    public MyLoanPresenter(MyLoanContract.View mLoanView, Repository mReposity) {
        this.mLoanView = mLoanView;
        this.mReposity = mReposity;
    }

    @Override
    public void start() {

    }
}
