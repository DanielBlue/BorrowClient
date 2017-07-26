package com.zoesap.borrowclient.applyinfo;

import android.content.Context;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface ApplyInfoContract {
    interface Presenter extends BasePresenter {
        void getSmsCode(String phonenum);

        void applySubmit(String name, String phonenum, String smscode, String money, String use, String loanId);

        void stopCountDownTimer();

    }

    interface View extends BaseView<Presenter> {
        void updateCountDownButton(String millis);

        void countDownFinish();

        void setApplyInfoId(int id);

        void change2NextPage();

        Context getContext();
    }
}
