package com.zoesap.borrowclient.self;

import android.text.TextUtils;

import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.data.source.Repository;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/18.
 */

public class SelfPresenter implements SelfContract.Presenter {
    private final SelfContract.View mSelfView;
    private final Repository mRepository;

    public SelfPresenter(SelfContract.View mSelfView, Repository mDataRepository) {
        this.mSelfView = NullUtils.checkNotNull(mSelfView);
        this.mRepository = NullUtils.checkNotNull(mDataRepository);
        mSelfView.setPresent(this);
    }

    @Override
    public void start() {
        initState();
    }

    private void initState() {
        String account = mRepository.getAccountFromSp();
        if (!TextUtils.isEmpty(mRepository.getAccountFromSp())
                && BorrowApplication.getInstance().ismSignIn()) {
            mSelfView.setAccount("用户名: "+account);
        } else {
            mSelfView.setLoginInfo();
        }
    }
}
