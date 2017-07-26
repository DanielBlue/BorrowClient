package com.zoesap.borrowclient.applyresult;

import com.zoesap.borrowclient.BasePresenter;
import com.zoesap.borrowclient.BaseView;

/**
 * Created by maoqi on 2017/7/25.
 */

public interface ApplyResultContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
    }
}
