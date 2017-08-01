package com.zoesap.borrowclient.setting;

import android.app.Activity;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface SettingContract {
    interface Presenter extends BasePresenter {
        void clearAccountAndPassword();
    }

    interface View extends BaseView<Presenter> {
        Activity getParentActivity();

        void showVersionCode(String version);
    }
}
