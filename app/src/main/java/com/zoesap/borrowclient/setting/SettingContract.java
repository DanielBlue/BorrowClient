package com.zoesap.borrowclient.setting;

import android.app.Activity;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface SettingContract {
    interface Presenter extends CoreBasePresenter {
        void clearAccountAndPassword();
    }

    interface View extends CoreBaseView<Presenter> {
        Activity getParentActivity();

        void showVersionCode(String version);
    }
}
