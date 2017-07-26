package com.zoesap.borrowclient.setting;

import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/25.
 */

public class SettingPresenter implements SettingContract.Presenter {
    private final SettingContract.View mSettingView;
    private final Repository mRepository;

    public SettingPresenter(SettingContract.View mSettingView, Repository mRepository) {
        this.mSettingView = NullUtils.checkNotNull(mSettingView);
        this.mRepository = NullUtils.checkNotNull(mRepository);

        mSettingView.setPresent(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void clearAccountAndPassword() {
        mRepository.savePassword2Sp("");
    }
}
