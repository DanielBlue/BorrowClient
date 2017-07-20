package com.zoesap.borrowclient;

import android.support.annotation.NonNull;

/**
 * Created by maoqi on 2017/6/27.
 */

public interface BaseView<T> {
    void setPresent(@NonNull T presenter);

    void showNetError();

    void showLoadindDialog();

    void loadingDialogDismiss();
}
