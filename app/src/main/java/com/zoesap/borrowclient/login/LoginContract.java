package com.zoesap.borrowclient.login;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

/**
 * Created by maoqi on 2017/7/20.
 */

public interface LoginContract {
    class Constants{
        public static final String KEY_ACCOUNT = "key_account";
        public static final String KEY_PASSWORD = "key_password";
        public static final String KEY_UID = "key_uid";

    }
    
    interface Presenter extends CoreBasePresenter {
        void login(String account, String password);
    }

    interface View extends CoreBaseView<Presenter> {
        void showPreInptAccount(String account);

        void showToast(String info);

        void activityFinish();

    }
}
