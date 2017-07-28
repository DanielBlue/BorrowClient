package com.zoesap.borrowclient.resetpassword;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

/**
 * Created by maoqi on 2017/7/27.
 */

public interface ResetPasswordContract {
    interface Presenter extends BasePresenter {
        void stopCountDownTimer();

        void getSmsCode(String phoneNum);

        void submit(String phoneNumber, String code, String newPassword, String confirmPassword);
    }

    interface View extends BaseView<Presenter> {
        void updateCountDownButton(String millis);

        void countDownFinish();

        void activityFinish();

        void showPreInptAccount(String account);
    }
}
