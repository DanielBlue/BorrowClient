package com.zoesap.borrowclient.register;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

/**
 * Created by maoqi on 2017/7/26.
 */

public interface RegisterContract {
    interface Presenter extends CoreBasePresenter {
        void getSmsCode(String phoneNum);

        void stopCountDownTimer();

        void applySubmit(String mobileNumber, String password, String verifyPassword, String getCode, String visitCode);
    }

    interface View extends CoreBaseView<Presenter> {
        void updateCountDownButton(String millis);

        void countDownFinish();

        void clearInput();

        void activityFinish();
    }
}
