package com.zoesap.borrowclient.login;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

/**
 * Created by maoqi on 2017/7/20.
 */

public interface LoginContract {
    class Constants{
        public static final String KEY_ACCOUNT = "key_account";
        public static final String KEY_PASSWORD = "key_password";
        public static final String KEY_UID = "key_uid";

    }
    
    interface Presenter extends BasePresenter {
        void login(String account, String password);
    }

    interface View extends BaseView<Presenter> {
        void showPreInptAccount(String account);

        void showToast(String info);
    }
}
