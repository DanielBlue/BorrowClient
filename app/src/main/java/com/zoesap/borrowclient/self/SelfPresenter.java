package com.zoesap.borrowclient.self;

import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/18.
 */

public class SelfPresenter implements SelfContract.Presenter {
    private final SelfContract.View mSelfView;
    private final Repository mDataRepository;

    public SelfPresenter(SelfContract.View mSelfView, Repository mDataRepository) {
        this.mSelfView = NullUtils.checkNotNull(mSelfView);
        this.mDataRepository = NullUtils.checkNotNull(mDataRepository);
        mSelfView.setPresent(this);
    }

    @Override
    public void start() {

    }
}
