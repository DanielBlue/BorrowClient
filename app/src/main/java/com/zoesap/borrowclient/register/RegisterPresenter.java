package com.zoesap.borrowclient.register;

import android.support.annotation.NonNull;

import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/26.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private final RegisterContract.View mRegisterView;
    private final Repository mRepository;

    public RegisterPresenter(@NonNull RegisterContract.View mRegisterView,@NonNull Repository mRepository) {
        this.mRegisterView = NullUtils.checkNotNull(mRegisterView);
        this.mRepository = NullUtils.checkNotNull(mRepository);
        mRegisterView.setPresent(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getSmsCode(String phoneNum) {

    }

    @Override
    public void stopCountDownTimer() {
        
    }
}
