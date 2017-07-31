package com.zoesap.borrowclient.splash;

import android.app.Activity;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

/**
 * Created by maoqi on 2017/7/31.
 */

public interface SplashContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        Activity getParentActivity();
    }
}
