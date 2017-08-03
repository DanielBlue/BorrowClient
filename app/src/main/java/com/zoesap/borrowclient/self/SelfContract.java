package com.zoesap.borrowclient.self;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

/**
 * Created by maoqi on 2017/7/18.
 */

interface SelfContract {
    interface Presenter extends CoreBasePresenter {
    }

    interface View extends CoreBaseView<Presenter> {
        void setAccount(String account);

        void setLoginInfo();
    }
}
