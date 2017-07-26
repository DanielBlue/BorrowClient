package com.zoesap.borrowclient.setting;

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
    }
}
