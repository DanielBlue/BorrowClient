package com.zoesap.borrowclient.resetpassword;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

/**
 * Created by maoqi on 2017/7/27.
 */

public interface ResetPasswordContract {
    interface Presenter extends CoreBasePresenter {
        void stopCountDownTimer();

        void getSmsCode(String phoneNum);

        void submit(String phoneNumber, String code, String newPassword, String confirmPassword);
    }

    interface View extends CoreBaseView<Presenter> {
        void updateCountDownButton(String millis);

        void countDownFinish();

        void activityFinish();

        void showPreInptAccount(String account);
    }
}
