package com.zoesap.borrowclient.splash;

import android.app.Activity;

import pers.maoqi.core.CoreBasePresenter;
import pers.maoqi.core.CoreBaseView;

/**
 * Created by maoqi on 2017/7/31.
 */

public interface SplashContract {
    interface Presenter extends CoreBasePresenter {

    }

    interface View extends CoreBaseView<Presenter> {
        Activity getParentActivity();
    }
}
