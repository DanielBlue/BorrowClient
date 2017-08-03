package com.zoesap.borrowclient.applyinfo;

import android.content.Context;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface ApplyInfoContract {
    interface Presenter extends CoreBasePresenter {
        void getSmsCode(String phonenum);

        void applySubmit(String name, String phonenum, String smscode, String money, String use, String loanId);

        void stopCountDownTimer();

    }

    interface View extends CoreBaseView<Presenter> {
        void updateCountDownButton(String millis);

        void countDownFinish();

        void setApplyInfoId(int id);

        void change2NextPage();

        Context getContext();
    }
}
