package com.zoesap.borrowclient.applyqualification;

import android.content.Context;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface ApplyQualificationContract {
    interface Presenter extends BasePresenter{
        void submit2Server(String mCurrentIncome, String mCurrentJob, String mCurrentHouse, String loanId);
    }
    interface View extends BaseView<Presenter>{
        void change2NextPage();

        Context getContext();
    }
}
