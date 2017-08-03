package com.zoesap.borrowclient.applyqualification;

import android.content.Context;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface ApplyQualificationContract {
    interface Presenter extends CoreBasePresenter {
        void submit2Server(String mCurrentIncome, String mCurrentJob, String mCurrentHouse, String applyInfoId);
    }
    interface View extends CoreBaseView<Presenter> {
        void change2NextPage();

        Context getContext();
    }
}
